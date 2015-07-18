package org.keycloak.services.managers;

import java.io.FileNotFoundException;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.rmi.NotBoundException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Date;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.jboss.resteasy.spi.HttpRequest;
import org.keycloak.ClientConnection;
import org.keycloak.RSATokenVerifier;
import org.keycloak.VerificationException;
import org.keycloak.events.Details;
import org.keycloak.events.EventBuilder;
import org.keycloak.events.EventType;
import org.keycloak.jose.jws.JWSBuilder;
import org.keycloak.login.LoginFormsProvider;
import org.keycloak.models.ApplicationModel;
import org.keycloak.models.ClientModel;
import org.keycloak.models.ClientSessionModel;
import org.keycloak.models.CustomUserModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RequiredCredentialModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.models.utils.KeycloakModelUtils;
import org.keycloak.protocol.LoginProtocol;
import org.keycloak.protocol.oidc.TokenManager;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.services.resources.RealmsResource;
import org.keycloak.services.resources.flows.Flows;
import org.keycloak.services.util.CookieHelper;
import org.keycloak.util.Time;

import com.client.ClientAPI;
import com.client.ClientNoAccessException;
import com.client.Converter;
import com.client.NoServerException;
import com.client.TokenOTIP;
import com.client.QueryUserStatus;
import com.client.QueryDomain;
import com.og.smssender.SMSSend;

/**
 * Stateless object that manages authentication
 * 
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class AuthenticationManager {
	protected static Logger logger = Logger
			.getLogger(AuthenticationManager.class);
	public static final String FORM_USERNAME = "username";
	// used for auth login
	public static final String KEYCLOAK_IDENTITY_COOKIE = "KEYCLOAK_IDENTITY";
	// used solely to determine is user is logged in
	public static final String KEYCLOAK_SESSION_COOKIE = "KEYCLOAK_SESSION";
	public static final String KEYCLOAK_REMEMBER_ME = "KEYCLOAK_REMEMBER_ME";
	private static final String ENCODING_ASCII = "ASCII";
	private static final int MOBILE_CODE = 50;
	private static final String SERIAL_NO = "SFAOTP";
	private static final byte[] EMPTY_BYTES = "".getBytes();

	protected BruteForceProtector protector;

	public AuthenticationManager() {
	}

	public AuthenticationManager(BruteForceProtector protector) {
		this.protector = protector;
	}

	public static boolean isSessionValid(RealmModel realm,
			UserSessionModel userSession) {

		System.out
				.println("CHecking by session:================================");

		if (userSession == null) {
			logger.debug("No user session");
			return false;
		}

		System.out.println("CHecking by session username :"
				+ userSession.getLoginUsername());

		int currentTime = Time.currentTime();
		int max = userSession.getStarted() + realm.getSsoSessionMaxLifespan();
		return userSession != null
				&& userSession.getLastSessionRefresh()
						+ realm.getSsoSessionIdleTimeout() > currentTime
				&& max > currentTime;
	}

	public static void logout(KeycloakSession session, RealmModel realm,
			UserSessionModel userSession, UriInfo uriInfo,
			ClientConnection connection) {
		if (userSession == null)
			return;
		UserModel user = userSession.getUser();

		logger.debugv("Logging out: {0} ({1})", user.getUsername(),
				userSession.getId());
		expireIdentityCookie(realm, uriInfo, connection);
		expireRememberMeCookie(realm, uriInfo, connection);

		for (ClientSessionModel clientSession : userSession.getClientSessions()) {
			ClientModel client = clientSession.getClient();
			if (client instanceof ApplicationModel) {
				String authMethod = clientSession.getAuthMethod();
				if (authMethod == null)
					continue; // must be a keycloak service like account
				LoginProtocol protocol = session.getProvider(
						LoginProtocol.class, authMethod);
				protocol.setRealm(realm).setUriInfo(uriInfo);
				protocol.backchannelLogout(userSession, clientSession);
			}
		}

		session.sessions().removeUserSession(realm, userSession);
	}

	public static void logoutLLO(KeycloakSession session, RealmModel realm,
			UserSessionModel userSession, UriInfo uriInfo,
			ClientConnection connection, ClientModel app) {
		if (userSession == null)
			return;
		UserModel user = userSession.getUser();

		logger.debugv("Logging out: {0} ({1})", user.getUsername(),
				userSession.getId());
		ClientSessionModel clnt = null;
		int size = userSession.getClientSessions().size();
		for (ClientSessionModel clientSession : userSession.getClientSessions()) {
			ClientModel client = clientSession.getClient();
			if (client instanceof ApplicationModel) {
				if (app.getClientId().equals(client.getClientId())) {
					String authMethod = clientSession.getAuthMethod();
					if (authMethod == null)
						continue; // must be a keycloak service like account
					LoginProtocol protocol = session.getProvider(
							LoginProtocol.class, authMethod);
					protocol.setRealm(realm).setUriInfo(uriInfo);
					protocol.backchannelLogout(userSession, clientSession);

					clnt = clientSession;
				}
			}
		}

		if (size == 0) {
			expireIdentityCookie(realm, uriInfo, connection);
			expireRememberMeCookie(realm, uriInfo, connection);
			session.sessions().removeUserSessions(realm, userSession.getUser());
		} else {
			if (clnt != null) {
				logger.info("delete a session user to application \""
						+ app.getClientId() + "\"");
				// session.sessions().removeUserSessions(realm,
				// userSession.getUser());
				session.sessions().removeUserSession(realm, user,
						clnt.getClient());
			}
		}
	}

	public static AccessToken createIdentityToken(RealmModel realm,
			UserModel user, UserSessionModel session) {
		AccessToken token = new AccessToken();
		token.id(KeycloakModelUtils.generateId());
		token.issuedNow();
		token.subject(user.getId());
		token.issuer(realm.getName());
		if (session != null) {
			token.setSessionState(session.getId());
		}
		if (realm.getSsoSessionMaxLifespan() > 0) {
			token.expiration(Time.currentTime()
					+ realm.getSsoSessionMaxLifespan());
		}
		return token;
	}

	public static void createLoginCookie(RealmModel realm, UserModel user,
			UserSessionModel session, UriInfo uriInfo,
			ClientConnection connection) {
		String cookiePath = getIdentityCookiePath(realm, uriInfo);
		AccessToken identityToken = createIdentityToken(realm, user, session);
		String encoded = encodeToken(realm, identityToken);
		boolean secureOnly = realm.getSslRequired().isRequired(connection);
		int maxAge = NewCookie.DEFAULT_MAX_AGE;
		if (session.isRememberMe()) {
			maxAge = realm.getSsoSessionMaxLifespan();
		}
		logger.debugv(
				"Create login cookie - name: {0}, path: {1}, max-age: {2}",
				KEYCLOAK_IDENTITY_COOKIE, cookiePath, maxAge);
		CookieHelper.addCookie(KEYCLOAK_IDENTITY_COOKIE, encoded, cookiePath,
				null, null, maxAge, secureOnly, true);
		// builder.cookie(new NewCookie(cookieName, encoded, cookiePath, null,
		// null, maxAge, secureOnly));// todo httponly , true);

		String sessionCookieValue = realm.getName() + "/" + user.getId();
		if (session != null) {
			sessionCookieValue += "/" + session.getId();
		}
		// THIS SHOULD NOT BE A HTTPONLY COOKIE! It is used for OpenID Connect
		// Iframe Session support!
		// Max age should be set to the max lifespan of the session as it's used
		// to invalidate old-sessions on re-login
		CookieHelper.addCookie(KEYCLOAK_SESSION_COOKIE, sessionCookieValue,
				cookiePath, null, null, realm.getSsoSessionMaxLifespan(),
				secureOnly, false);

	}

	public static void createRememberMeCookie(RealmModel realm,
			String username, UriInfo uriInfo, ClientConnection connection) {
		String path = getIdentityCookiePath(realm, uriInfo);
		boolean secureOnly = realm.getSslRequired().isRequired(connection);
		// remember me cookie should be persistent (hardcoded to 365 days for
		// now)
		// NewCookie cookie = new NewCookie(KEYCLOAK_REMEMBER_ME, "true", path,
		// null, null, realm.getCentralLoginLifespan(), secureOnly);// todo
		// httponly , true);
		CookieHelper.addCookie(KEYCLOAK_REMEMBER_ME, "username:" + username,
				path, null, null, 31536000, secureOnly, true);
	}

	public static String getRememberMeUsername(RealmModel realm,
			HttpHeaders headers) {
		if (realm.isRememberMe()) {
			Cookie cookie = headers.getCookies().get(
					AuthenticationManager.KEYCLOAK_REMEMBER_ME);
			if (cookie != null) {
				String value = cookie.getValue();
				String[] s = value.split(":");
				if (s[0].equals("username") && s.length == 2) {
					return s[1];
				}
			}
		}
		return null;
	}

	protected static String encodeToken(RealmModel realm, Object token) {
		String encodedToken = new JWSBuilder().jsonContent(token).rsa256(
				realm.getPrivateKey());
		return encodedToken;
	}

	public static void expireIdentityCookie(RealmModel realm, UriInfo uriInfo,
			ClientConnection connection) {
		logger.debug("Expiring identity cookie");
		String path = getIdentityCookiePath(realm, uriInfo);
		expireCookie(realm, KEYCLOAK_IDENTITY_COOKIE, path, true, connection);
		expireCookie(realm, KEYCLOAK_SESSION_COOKIE, path, false, connection);
	}

	public static void expireRememberMeCookie(RealmModel realm,
			UriInfo uriInfo, ClientConnection connection) {
		logger.debug("Expiring remember me cookie");
		String path = getIdentityCookiePath(realm, uriInfo);
		String cookieName = KEYCLOAK_REMEMBER_ME;
		expireCookie(realm, cookieName, path, true, connection);
	}

	protected static String getIdentityCookiePath(RealmModel realm,
			UriInfo uriInfo) {
		return getRealmCookiePath(realm, uriInfo);
	}

	public static String getRealmCookiePath(RealmModel realm, UriInfo uriInfo) {
		URI uri = RealmsResource.realmBaseUrl(uriInfo).build(realm.getName());
		return uri.getRawPath();
	}

	public static void expireCookie(RealmModel realm, String cookieName,
			String path, boolean httpOnly, ClientConnection connection) {
		logger.debugv("Expiring cookie: {0} path: {1}", cookieName, path);
		boolean secureOnly = realm.getSslRequired().isRequired(connection);
		;
		CookieHelper.addCookie(cookieName, "", path, null, "Expiring cookie",
				0, secureOnly, httpOnly);
	}

	public AuthResult authenticateIdentityCookie(KeycloakSession session,
			RealmModel realm, UriInfo uriInfo, ClientConnection connection,
			HttpHeaders headers) {
		return authenticateIdentityCookie(session, realm, uriInfo, connection,
				headers, true);
	}

	public AuthResult authenticateIdentityCookie(KeycloakSession session,
			RealmModel realm, UriInfo uriInfo, ClientConnection connection,
			HttpHeaders headers, boolean checkActive) {
		Cookie cookie = headers.getCookies().get(KEYCLOAK_IDENTITY_COOKIE);
		if (cookie == null || "".equals(cookie.getValue())) {
			logger.debugv("Could not find cookie: {0}",
					KEYCLOAK_IDENTITY_COOKIE);
			return null;
		}

		String tokenString = cookie.getValue();
		AuthResult authResult = verifyIdentityToken(session, realm, uriInfo,
				connection, checkActive, tokenString);
		if (authResult == null) {
			expireIdentityCookie(realm, uriInfo, connection);
			return null;
		}
		authResult.getSession().setLastSessionRefresh(Time.currentTime());
		return authResult;
	}

	public Response checkNonFormAuthentication(KeycloakSession session,
			ClientSessionModel clientSession, RealmModel realm,
			UriInfo uriInfo, HttpRequest request,
			ClientConnection clientConnection, HttpHeaders headers,
			EventBuilder event) {
		AuthResult authResult = authenticateIdentityCookie(session, realm,
				uriInfo, clientConnection, headers, true);
		if (authResult != null) {
			UserModel user = authResult.getUser();
			UserSessionModel userSession = authResult.getSession();
			TokenManager.attachClientSession(userSession, clientSession,
					request);
			event.user(user).session(userSession)
					.detail(Details.AUTH_METHOD, "sso");
			return nextActionAfterAuthentication(session, userSession,
					clientSession, clientConnection, request, uriInfo, event);
		}
		return null;
	}

	public static Response redirectAfterSuccessfulFlow(KeycloakSession session,
			RealmModel realm, UserSessionModel userSession,
			ClientSessionModel clientSession, HttpRequest request,
			UriInfo uriInfo, ClientConnection clientConnection) {
		Cookie sessionCookie = request.getHttpHeaders().getCookies()
				.get(AuthenticationManager.KEYCLOAK_SESSION_COOKIE);
		if (sessionCookie != null) {

			String[] split = sessionCookie.getValue().split("/");
			if (split.length >= 3) {
				String oldSessionId = split[2];
				if (!oldSessionId.equals(userSession.getId())) {
					UserSessionModel oldSession = session.sessions()
							.getUserSession(realm, oldSessionId);
					if (oldSession != null) {
						logger.debugv(
								"Removing old user session: session: {0}",
								oldSessionId);
						session.sessions().removeUserSession(realm, oldSession);
					}
				}
			}
		}

		// refresh the cookies!
		createLoginCookie(realm, userSession.getUser(), userSession, uriInfo,
				clientConnection);
		if (userSession.isRememberMe())
			createRememberMeCookie(realm, userSession.getUser().getUsername(),
					uriInfo, clientConnection);
		LoginProtocol protocol = session.getProvider(LoginProtocol.class,
				clientSession.getAuthMethod());
		protocol.setRealm(realm).setUriInfo(uriInfo);
		return protocol.authenticated(userSession, new ClientSessionCode(realm,
				clientSession));

	}

	public static Response nextActionAfterAuthentication(
			KeycloakSession session, UserSessionModel userSession,
			ClientSessionModel clientSession,
			ClientConnection clientConnection, HttpRequest request,
			UriInfo uriInfo, EventBuilder event) {

		System.out.println("KeyCloack: nextActionAfterAuthentication ");

		RealmModel realm = clientSession.getRealm();
		UserModel user = userSession.getUser();
		isTotpConfigurationRequired(realm, user);
		isEmailVerificationRequired(realm, user);
		ClientModel client = clientSession.getClient();

		boolean isResource = client instanceof ApplicationModel;
		ClientSessionCode accessCode = new ClientSessionCode(realm,
				clientSession);

		System.out.println("processAccessCode");
		logger.debugv("processAccessCode: isResource: {0}", isResource);
		logger.debugv("processAccessCode: go to oauth page?: {0}", !isResource);

		event.detail(Details.CODE_ID, clientSession.getId());
		Set<UserModel.RequiredAction> requiredActions = user
				.getRequiredActions();
		if (!requiredActions.isEmpty()) {
			UserModel.RequiredAction action = user.getRequiredActions()
					.iterator().next();
			accessCode.setRequiredAction(action);
			LoginFormsProvider loginFormsProvider = Flows
					.forms(session, realm, client, uriInfo)
					.setClientSessionCode(accessCode.getCode()).setUser(user);
			if (action.equals(UserModel.RequiredAction.VERIFY_EMAIL)) {
				event.clone().event(EventType.SEND_VERIFY_EMAIL)
						.detail(Details.EMAIL, user.getEmail()).success();
			}
			return loginFormsProvider.createResponse(action);
		}

		if (!isResource) {
			accessCode.setAction(ClientSessionModel.Action.OAUTH_GRANT);
			List<RoleModel> realmRoles = new LinkedList<RoleModel>();
			MultivaluedMap<String, RoleModel> resourceRoles = new MultivaluedMapImpl<String, RoleModel>();
			for (RoleModel r : accessCode.getRequestedRoles()) {
				if (r.getContainer() instanceof RealmModel) {
					realmRoles.add(r);
				} else {
					resourceRoles.add(
							((ApplicationModel) r.getContainer()).getName(), r);
				}
			}
			return Flows.forms(session, realm, client, uriInfo)
					.setClientSessionCode(accessCode.getCode())
					.setAccessRequest(realmRoles, resourceRoles)
					.setClient(client).createOAuthGrant();
		}
		System.out.println("redirectAfterSuccessfulFlow");
		event.success();
		System.out.println("redirectAfterSuccessfulFlow");
		return redirectAfterSuccessfulFlow(session, realm, userSession,
				clientSession, request, uriInfo, clientConnection);

	}

	protected static void isTotpConfigurationRequired(RealmModel realm,
			UserModel user) {
		for (RequiredCredentialModel c : realm.getRequiredCredentials()) {
			if (c.getType().equals(CredentialRepresentation.TOTP)
					&& !user.isTotp()) {
				//user.addRequiredAction(UserModel.RequiredAction.CONFIGURE_TOTP);
				logger.debug("User is required to configure totp");
			}
		}
	}

	protected static void isEmailVerificationRequired(RealmModel realm,
			UserModel user) {
		if (realm.isVerifyEmail() && !user.isEmailVerified()) {
			user.addRequiredAction(UserModel.RequiredAction.VERIFY_EMAIL);
			logger.debug("User is required to verify email");
		}
	}

	protected AuthResult verifyIdentityToken(KeycloakSession session,
			RealmModel realm, UriInfo uriInfo, ClientConnection connection,
			boolean checkActive, String tokenString) {
		try {
			AccessToken token = RSATokenVerifier.verifyToken(tokenString,
					realm.getPublicKey(), realm.getName(), checkActive);
			if (checkActive) {
				if (!token.isActive()
						|| token.getIssuedAt() < realm.getNotBefore()) {
					logger.debug("identity cookie expired");
					return null;
				} else {
					logger.debugv(
							"token active - active: {0}, issued-at: {1}, not-before: {2}",
							token.isActive(), token.getIssuedAt(),
							realm.getNotBefore());
				}
			}

			UserModel user = session.users().getUserById(token.getSubject(),
					realm);
			if (user == null || !user.isEnabled()) {
				logger.debug("Unknown user in identity token");
				return null;
			}

			UserSessionModel userSession = session.sessions().getUserSession(
					realm, token.getSessionState());
			if (!isSessionValid(realm, userSession)) {
				if (userSession != null)
					logout(session, realm, userSession, uriInfo, connection);
				logger.debug("User session not active");
				return null;
			}

			return new AuthResult(user, userSession, token);
		} catch (VerificationException e) {
			logger.debug("Failed to verify identity token", e);
		}
		return null;
	}

	public AuthenticationStatus authenticateForm(KeycloakSession session,
			ClientConnection clientConnection, RealmModel realm,
			MultivaluedMap<String, String> formData, StringBuilder errorMessage) {
		String username = formData.getFirst(FORM_USERNAME);
		if (username == null) {
			logger.debug("Username not provided");
			return AuthenticationStatus.INVALID_USER;
		}

		if (realm.isBruteForceProtected()) {
			if (protector.isTemporarilyDisabled(session, realm, username)) {
				return AuthenticationStatus.ACCOUNT_TEMPORARILY_DISABLED;
			}
		}

		AuthenticationStatus status = authenticateInternal(session, realm,
				formData, username, errorMessage);
		
		/*AuthenticationStatus status = authenticateInternal(session, realm,
				formData, username);*/

		System.out.println("Keycloack: session =" + session);
		System.out.println("KeyCloack: realm name=" + realm.getName());
		System.out.println("Keycloack: username:" + username);
		System.out.println("Keycloack: auth status:" + status);
		System.out.println("Keycloack: auth isBruteForceProtected: "
				+ realm.isBruteForceProtected());
		if (realm.isBruteForceProtected()) {

			switch (status) {
			case SUCCESS:
				protector.successfulLogin(realm, username, clientConnection);
				break;
			case FAILED:
			case MISSING_TOTP:
			case MISSING_PASSWORD:
			case INVALID_CREDENTIALS:
				protector.failedLogin(realm, username, clientConnection);
				break;
			case INVALID_USER:
				protector.invalidUser(realm, username, clientConnection);
				break;
			default:
				break;
			}
		}

		return status;
	}

	protected AuthenticationStatus authenticateInternal(
			KeycloakSession session, RealmModel realm,
			MultivaluedMap<String, String> formData, String username,
			StringBuilder errorMessage) {

		AuthenticationStatus as = null;
		String realmName = realm.getName();

		System.out.println("KeyCloack Authentication Realm: " + realmName);

		try {
		   if (realmName.equals("master")) {
		     as = this.authenticateInternalMaster(session, realm, formData,
					username);
		   } else {
			 as = this.authenticateInternalNoneMaster(session, realm,
					formData, username, errorMessage);
		   }
		}
		catch(Exception e) {}
		
		return as;

	}
	
	protected AuthenticationStatus authenticateInternal(KeycloakSession session, RealmModel realm, MultivaluedMap<String, String> formData, String username) {
        UserModel user = KeycloakModelUtils.findUserByNameOrEmail(session, realm, username);

        if (user == null) {
            logger.debugv("User {0} not found", username);
            return AuthenticationStatus.INVALID_USER;
        }

        if (!user.isEnabled()) {
            return AuthenticationStatus.ACCOUNT_DISABLED;
        }

        Set<String> types = new HashSet<String>();

        for (RequiredCredentialModel credential : realm.getRequiredCredentials()) {
            types.add(credential.getType());
        }

        if (types.contains(CredentialRepresentation.PASSWORD)) {
            List<UserCredentialModel> credentials = new LinkedList<UserCredentialModel>();

            String password = formData.getFirst(CredentialRepresentation.PASSWORD);
            if (password != null) {
                credentials.add(UserCredentialModel.password(password));
            }

            String passwordToken = formData.getFirst(CredentialRepresentation.PASSWORD_TOKEN);
            if (passwordToken != null) {
                credentials.add(UserCredentialModel.passwordToken(passwordToken));
            }

            String totp = formData.getFirst(CredentialRepresentation.TOTP);
            if (totp != null) {
                credentials.add(UserCredentialModel.totp(totp));
            }

            if (password == null && passwordToken == null) {
                logger.debug("Password not provided");
                return AuthenticationStatus.MISSING_PASSWORD;
            }

            logger.debugv("validating password for user: {0}", username);

            if (!session.users().validCredentials(realm, user, credentials)) {
                return AuthenticationStatus.INVALID_CREDENTIALS;
            }

            if (user.isTotp() && totp == null) {
                return AuthenticationStatus.MISSING_TOTP;
            }
            
            return AuthenticationStatus.SUCCESS;

            /*
            if (!user.getRequiredActions().isEmpty()) {
                return AuthenticationStatus.ACTIONS_REQUIRED;
            } else {
                return AuthenticationStatus.SUCCESS;
            }
            */
        } else if (types.contains(CredentialRepresentation.SECRET)) {
            String secret = formData.getFirst(CredentialRepresentation.SECRET);
            if (secret == null) {
                logger.debug("Secret not provided");
                return AuthenticationStatus.MISSING_PASSWORD;
            }
            if (!session.users().validCredentials(realm, user, UserCredentialModel.secret(secret))) {
                return AuthenticationStatus.INVALID_CREDENTIALS;
            }
            if (!user.getRequiredActions().isEmpty()) {
                return AuthenticationStatus.ACTIONS_REQUIRED;
            } else {
                return AuthenticationStatus.SUCCESS;
            }
        } else {
            logger.warn("Do not know how to authenticate user");
            return AuthenticationStatus.FAILED;
        }
    }

	protected AuthenticationStatus authenticateInternalMaster(
			KeycloakSession session, RealmModel realm,
			MultivaluedMap<String, String> formData, String username) throws Exception {
		UserModel user = KeycloakModelUtils.findUserByNameOrEmail(session,
				realm, username);

		if (user == null) {
			logger.debugv("User {0} not found", username);
			return AuthenticationStatus.INVALID_USER;
		}

		if (!user.isEnabled()) {
			return AuthenticationStatus.ACCOUNT_DISABLED;
		}

		Set<String> types = new HashSet<String>();

		for (RequiredCredentialModel credential : realm
				.getRequiredCredentials()) {
			types.add(credential.getType());
		}

		if (types.contains(CredentialRepresentation.PASSWORD)) {
			List<UserCredentialModel> credentials = new LinkedList<UserCredentialModel>();

			String password = formData
					.getFirst(CredentialRepresentation.PASSWORD);
			if (password != null) {
				credentials.add(UserCredentialModel.password(password));
			}

			String passwordToken = formData
					.getFirst(CredentialRepresentation.PASSWORD_TOKEN);
			if (passwordToken != null) {
				credentials.add(UserCredentialModel
						.passwordToken(passwordToken));
			}

			String totp = formData.getFirst(CredentialRepresentation.TOTP);
			if (totp != null) {
				credentials.add(UserCredentialModel.totp(totp));
			}

			if (password == null && passwordToken == null) {
				logger.debug("Password not provided");
				return AuthenticationStatus.MISSING_PASSWORD;
			}

			logger.debugv("validating password for user: {0}", username);

			if (!session.users().validCredentials(realm, user, credentials)) {
				return AuthenticationStatus.INVALID_CREDENTIALS;
			}

			if (user.isTotp() && totp == null) {
				return AuthenticationStatus.MISSING_TOTP;
			}

			if (!user.getRequiredActions().isEmpty()) {
				return AuthenticationStatus.ACTIONS_REQUIRED;
			} else {
				return AuthenticationStatus.SUCCESS;
			}
		} else if (types.contains(CredentialRepresentation.SECRET)) {
			String secret = formData.getFirst(CredentialRepresentation.SECRET);
			if (secret == null) {
				logger.debug("Secret not provided");
				return AuthenticationStatus.MISSING_PASSWORD;
			}
			if (!session.users().validCredentials(realm, user,
					UserCredentialModel.secret(secret))) {
				return AuthenticationStatus.INVALID_CREDENTIALS;
			}
			if (!user.getRequiredActions().isEmpty()) {
				return AuthenticationStatus.ACTIONS_REQUIRED;
			} else {
				return AuthenticationStatus.SUCCESS;
			}
		} else {
			logger.warn("Do not know how to authenticate user");
			return AuthenticationStatus.FAILED;
		}
	}

	protected ClientAPI initClientAPI() throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, NotBoundException,
			ClientNoAccessException, NoServerException, Exception {
		String keystorePath;
		String hsm_password;
		String serverIP;

		keystorePath = getPropAuthenticationValues("keystorePath");
		hsm_password = getPropAuthenticationValues("password");
		serverIP = getPropAuthenticationValues("serverIP");

		return ClientAPI
				.getInstance(keystorePath, hsm_password, serverIP, true);
	}

	
	private AuthenticationStatus checkForTNCPage(UserModel user, UserModel userModel) throws Exception {
		// Check TNC page
		String acceptedTNC = "N";
		String needTNC = "Y";
		System.out.println("=====Check TNC conditions====");
		System.out.println("Check TNC conditions: getEmail="
				+ userModel.getEmail());
		System.out.println("Check TNC conditions: getUsername="
				+ userModel.getUsername());
		System.out.println("Check TNC conditions: mobile="
				+ userModel.getMobile());
		System.out.println("Check TNC conditions: getNeedTNC="
				+ userModel.getNeedTNC());
		System.out.println("Check TNC conditions: getNeed2FA="
				+ userModel.getNeed2FA());
		
		CustomUserModel customUserModel = null;
		List<CustomUserModel> customUserModels = userModel.getCustomUsers();
		if (customUserModels.size() > 0) {
			customUserModel = customUserModels.get(0);
		} else {
			customUserModel = user.addCustomUser(acceptedTNC);
			customUserModel.setAcceptedTNCdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			
			customUserModel.updateCustomUser();
		}
		
		acceptedTNC = customUserModel.getAcceptedTNC();
		needTNC = userModel.getNeedTNC();
		System.out.println("Check TNC conditions: acceptedTNC="
				+ acceptedTNC);
		System.out.println("Check TNC conditions: needTNC="
				+ needTNC);

		if (needTNC.equalsIgnoreCase("Y")
				&& acceptedTNC.equalsIgnoreCase("N")) {
			return AuthenticationStatus.TNC;
		}
		
		return null;
	}
	
	
	private boolean checkForSpecialFlows(KeycloakSession session, ClientAPI clientAPI, String username, int domain) throws Exception {
		boolean ret=false;
		/* accountStatus=1 is active / password is going expired / password is expired
		 * accountStatus=2 is force change password
		 * accountStatus=3 is account is suspended 
		 * accountStatus=4 is account is disabled/not active
		*/
		QueryUserStatus qus = clientAPI.getUserStatus(EMPTY_BYTES, username, domain);
		int accountStatus = 0;
		if(qus!=null)
			accountStatus=qus.getStatus();	

		if(accountStatus==1) {
			double returnCode = getPasswordDayRemaining (clientAPI, username, domain);
			if(returnCode<0) {
				//call url from pse function module=CHANGEPASSWORD to redirect to SFA
				System.out.println("Password has expired");
				//updateTNCFlag(session,username);
			}
			else {
			   System.out.println("Account Active is detected");
			}  
		}
		else if(accountStatus==2) {
		    //call url from pse function module=CHANGEPASSWORD to redirect to SFA
			ret=true;
			System.out.println("Force to change passsord is detected");
			//updateTNCFlag(session,username);
		}
		else if(accountStatus==3 || accountStatus==4) {
			//Show error message and the flow is ended at 1FA
			ret=true;
			System.out.println("Account Disabled or Suspended is detected");
		}
		
		return ret;
	}
	
	
	private void updateTNCFlag(KeycloakSession session, String username) throws Exception {
		// Update to database
		UserModel model = session.users().getUserByUsername(username);
		CustomUserModel customUserModel = model.getCustomUsers().get(0);
		logger.debug("AcceptedTNC =" + customUserModel.getAcceptedTNC());
		customUserModel.setAcceptedTNC("Y");
		customUserModel.setUpdateby(model.getUsername());
		customUserModel.setUpdateddate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		customUserModel.updateCustomUser();
		logger.debug("Updated acceptedTNC");
	}
	
	
	private static double getPasswordDayRemaining(ClientAPI api, String userId, int dom) {
		double returnCode = -9999999;
		if (api != null) {
			try {
			     QueryDomain domain = api.queryDomain("".getBytes(), dom);
			     int lifeTimie = domain == null ? 0 : domain.getPasswordLifetime();
			     Date passwordCreateDate = api.queryUser("".getBytes(), userId, 22).getPwdCreationDate();
	
			     Calendar calendar = Calendar.getInstance();
			     long currentTime = calendar.getTime().getTime();
			     long createTime = passwordCreateDate == null ? 0 : passwordCreateDate.getTime();
			     double createDay = (double) (currentTime - createTime) / (double) (1000 * 60 * 60 * 24);
			     returnCode = lifeTimie - createDay;
	
			} catch (Exception e) {
			     e.printStackTrace();
			}
		}
		
		return returnCode;
	}

	
	protected AuthenticationStatus authenticateInternalNoneMaster(
			KeycloakSession session, RealmModel realm,
			MultivaluedMap<String, String> formData, String username,
			StringBuilder errorMessage) throws Exception {

		String need_tnc = formData.getFirst("need_tnc");
		if (need_tnc != null) {
			System.out.println("KeyCloack: processLogin need_tnc: " + need_tnc);
			if (need_tnc.equalsIgnoreCase("Y")) {
				updateTNCFlag(session,username);
				// go to landing page
				return AuthenticationStatus.SUCCESS;
			} else {
				// forward to 1fa
				return AuthenticationStatus.TNC_CANCEL;
			}
		}

		// formData.add("mobile", "0984352423");

		// Uncomment for hard code rediret to TOTP screen
		// return AuthenticationStatus.MISSING_TOTP;
		ClientAPI clientAPI = null;
		int domain = 0;
		boolean enable2fa = true;
		String enable2faConfigValue = null;
		try {
			enable2faConfigValue = getPropAuthenticationValues("enable2fa");
		} catch (Exception e1) {
			System.out
					.println("Unable to retirve enable2fa configuration. enable2fa wil be anable by default");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("KeyCloack: ------ enable2faConfigValue: "+enable2faConfigValue);
		
		UserModel user = KeycloakModelUtils.findUserByNameOrEmail(session,
				realm, username);

		logger.debug("TEST : Go inside this method");
		if (user == null) {
			// logger.de
			logger.debugv("User {0} not found", username);
			return AuthenticationStatus.INVALID_USER;
		}

		if (!user.isEnabled()) {
			return AuthenticationStatus.ACCOUNT_DISABLED;
		}

		// TODO: Just for testing
		// user.setTotp(true);

		Set<String> types = new HashSet<String>();

		for (RequiredCredentialModel credential : realm
				.getRequiredCredentials()) {
			types.add(credential.getType());
		}

		if (types.contains(CredentialRepresentation.PASSWORD)) {

			List<UserCredentialModel> credentials = new LinkedList<UserCredentialModel>();

			String password = formData
					.getFirst(CredentialRepresentation.PASSWORD);
			if (password != null) {
				credentials.add(UserCredentialModel.password(password));
			}

			String passwordToken = formData
					.getFirst(CredentialRepresentation.PASSWORD_TOKEN);
			if (passwordToken != null) {
				credentials.add(UserCredentialModel
						.passwordToken(passwordToken));
			}

			String totp = formData.getFirst(CredentialRepresentation.TOTP);

			if (totp != null) {
				credentials.add(UserCredentialModel.totp(totp));
			}

			if (password == null && passwordToken == null) {
				logger.debug("Password not provided");
				return AuthenticationStatus.MISSING_PASSWORD;
			}

			logger.debug("validating password for user: " + username);

			// start updating by izeno - 25 Mar 2015

			String encryptedBlock = formData.getFirst("encryptedBlock");

			String randomKey = formData.getFirst("randomKey");
			logger.debug("KeyCloack encryptedBlock & randomKey:"
					+ encryptedBlock + " - " + randomKey);

			System.out.println("KeyCloack encryptedBlock & randomKey:"
					+ encryptedBlock + " - " + randomKey);

			try {
				clientAPI = initClientAPI();
				domain = Integer
						.parseInt(getPropAuthenticationValues("domain"));

				UserModel userModel = session.users().getUserByUsername(
						username, realm);

				if (totp == null) {

					System.out
							.println("KeyCloack: First Level of authentication");
					logger.debug("KeyCloack: First Level of authentication");
					// First Level Authentication: authenticate the username &

					int returnCode = 0;
					byte[] randomKeyBytes = Converter
							.hexStringToBytes(randomKey);
					byte[] encryptedBlockBytes = Converter
							.hexStringToBytes(encryptedBlock);

					try {
						returnCode = clientAPI
								.verifyRSAStatic(EMPTY_BYTES, username, domain,
										randomKeyBytes, encryptedBlockBytes,
										encryptedBlockBytes.length);
						System.out.println("KeyCloack: Returned Code= "
								+ returnCode);

						// returnCode = 0;

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.debug(e.getMessage());
						return AuthenticationStatus.FAILED;
					}
					
					boolean ret = checkForSpecialFlows(session, clientAPI, username, domain);
					
					// not success
					if (!(returnCode == 0 || returnCode == 2051)) {
						System.out.println("KeyCloack: Return false...");
						return AuthenticationStatus.FAILED;
					}
					
					String need2FA = userModel.getNeed2FA();
					if (need2FA != null && enable2faConfigValue == null) {
						enable2fa = userModel.getNeed2FA()
								.equalsIgnoreCase("Y") ? true : false;
					} else {
						enable2fa = Boolean.valueOf(enable2faConfigValue);
					}

					if (!enable2fa) {
						AuthenticationStatus astatus = checkForTNCPage(user,userModel);
						if(astatus!=null) {
							return astatus;
						}
						else {
						   return AuthenticationStatus.SUCCESS;
						}   
					}
				} else {

					System.out.println("KeyCloack: enable2faConfigValue="
							+ enable2faConfigValue);

					String need2FA = userModel.getNeed2FA();
					if (need2FA != null && enable2faConfigValue == null) {
						enable2fa = userModel.getNeed2FA()
								.equalsIgnoreCase("Y") ? true : false;
					} else {
						enable2fa = Boolean.valueOf(enable2faConfigValue);
					}

					System.out.println("KeyCloack: enable2fa=" + enable2fa);

					if (enable2fa) {
						// Second level authentication: validate token
						System.out
								.println("KeyCloack: Second Level of authentication");

						int resultCode = clientAPI.verifyClearOTIP2(
								EMPTY_BYTES, username, domain, EMPTY_BYTES,
								totp.getBytes(), totp.length());

						System.out
								.println("KeyCloack: verifyClearOTIP2 resultCode: "
										+ resultCode);

						if (!(resultCode == 0 || resultCode == 2051)) {
							errorMessage.delete(0, errorMessage.length());
							errorMessage.append(getErrorMessage(resultCode));
							System.out
									.println("KeyCloack: verifyClearOTIP2 false resultCode: "
											+ resultCode);
							return AuthenticationStatus.MISSING_TOTP;
						}
						else {
							boolean ret = checkForSpecialFlows(session, clientAPI, username, domain);
						}
						
						
						AuthenticationStatus astatus = checkForTNCPage(user,userModel);
						if(astatus!=null) {
							return astatus;
						}
						else {
						   return AuthenticationStatus.SUCCESS;
						}  
					}
				}

				System.out.println("KeyCloack: get UserModel");
				//UserModel userModel = session.users().getUserByUsername(
					//	username, realm);
				if (userModel != null) {
					String need2FA = userModel.getNeed2FA();
					if (need2FA != null && enable2faConfigValue == null) {
						enable2fa = userModel.getNeed2FA()
								.equalsIgnoreCase("Y") ? true : false;
					} else {
						enable2fa = Boolean.valueOf(enable2faConfigValue);
					}
				}

				System.out.println("KeyCloack: enable2faConfigValue="
						+ enable2faConfigValue);
				System.out.println("KeyCloack: isTotp: " + user.isTotp());
				System.out.println("KeyCloack: Totp: " + totp);
				System.out.println("KeyCloack: enable2fa: " + enable2fa);

				// username & password is valid then check totp
				if(totp == null && enable2fa) {
				//if (user.isTotp() && totp == null && enable2fa) {
					System.out
							.println("Username and Password is valid.Then send out OTP code");

					int mediaType = 19;
					// delete expiry token
					int resultCode = clientAPI.deleteTokenEx(EMPTY_BYTES,
							username, domain, SERIAL_NO, mediaType, 1);

					// if delete is successfull then generate new token
					if (resultCode == 0 || resultCode == 789) {
						String propertiesPath = getPropAuthenticationValues("propertiesPath");
						String mobileNumber = user.getMobile();
						formData.add("mobile", mobileNumber);
						System.out.println("KeyCloack: User:" + username
								+ " ; Mobile: " + mobileNumber);

						// generate new token
						TokenOTIP tokenOTIP = clientAPI.getTokenOTIP(
								EMPTY_BYTES, username, domain, SERIAL_NO,
								mediaType);

						String[] otips = new String[1];
						otips = tokenOTIP.getOTIPs();

						System.out.println("KeyCloack:OTP code get from HSM="
								+ otips[0]);

						String msg = "OTP: " + otips[0];
						// send out the token to user's mobile

						SMSSend smsSend = new SMSSend(propertiesPath);

						System.out.println("KeyCloack protertiesPath "
								+ propertiesPath);

						smsSend.sendSMS(mobileNumber, msg,
								smsSend.ENCODING_ASCII, MOBILE_CODE);

					}
					return AuthenticationStatus.MISSING_TOTP;

				}
				// end updating by izeno- 25 Mar 2015

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.debug(e.getMessage());
				return AuthenticationStatus.FAILED;

			}

			// if (!session.users().validCredentials(realm, user,
			// credentials)&enable2fa)
			// {
			// return AuthenticationStatus.INVALID_CREDENTIALS;
			// }

			if (!user.getRequiredActions().isEmpty()) {
				return AuthenticationStatus.ACTIONS_REQUIRED;
			} else {
				return AuthenticationStatus.SUCCESS;
			}
		} else if (types.contains(CredentialRepresentation.SECRET)) {
			String secret = formData.getFirst(CredentialRepresentation.SECRET);
			if (secret == null) {
				logger.debug("Secret not provided");
				return AuthenticationStatus.MISSING_PASSWORD;
			}
			if (!session.users().validCredentials(realm, user,
					UserCredentialModel.secret(secret))) {
				return AuthenticationStatus.INVALID_CREDENTIALS;
			}
			if (!user.getRequiredActions().isEmpty()) {
				return AuthenticationStatus.ACTIONS_REQUIRED;
			} else {
				return AuthenticationStatus.ACTIONS_REQUIRED;
			}
		} else {
			logger.warn("Do not know how to authenticate user");
			return AuthenticationStatus.FAILED;
		}
	}

	public String getPropAuthenticationValues(String value) throws IOException {

		Properties prop = new Properties();
		String propFileName = "authenticationConfig.properties";

		InputStream inputStream = (InputStream) getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		return prop.getProperty(value);
	}

	public String getPropMobileValues(String username) throws IOException {

		Properties prop = new Properties();
		String propFileName = "mobileNumberResource.properties";

		InputStream inputStream = (InputStream) getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		return prop.getProperty(username);
	}

	public enum AuthenticationStatus {
		SUCCESS, ACCOUNT_TEMPORARILY_DISABLED, ACCOUNT_DISABLED, ACTIONS_REQUIRED, INVALID_USER, INVALID_CREDENTIALS, MISSING_PASSWORD, MISSING_TOTP, FAILED, TNC,
		TNC_CANCEL
	}

	public class AuthResult {
		private final UserModel user;
		private final UserSessionModel session;
		private final AccessToken token;

		public AuthResult(UserModel user, UserSessionModel session,
				AccessToken token) {
			this.user = user;
			this.session = session;
			this.token = token;
		}

		public UserSessionModel getSession() {
			return session;
		}

		public UserModel getUser() {
			return user;
		}

		public AccessToken getToken() {
			return token;
		}
	}

	private static String randomString(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVW1234567890";
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = chars.charAt(r.nextInt(chars.length()));
			sb.append(c);
		}
		return sb.toString();
	}

	public String getTotpSecretEncoded(String totpSecret,
			String totpSecretEncoded) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < totpSecretEncoded.length(); i += 4) {
			sb.append(totpSecretEncoded.substring(i, i + 4 < totpSecretEncoded
					.length() ? i + 4 : totpSecretEncoded.length()));
			if (i + 4 < totpSecretEncoded.length()) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
	public  String getModule(KeycloakSession session,String moduleName){
    	RealmManager moduleManager = new RealmManager(session);
		ModuleModel module = moduleManager.getModuleByName(moduleName);
		if (module == null) {
			return null;
		}
		return module.getFullpath();
    }

	public static String getErrorMessage(int errorCode) {
		String hexErrorCode = Integer.toHexString(errorCode);
		String errorMessage = "System is unable to process your request. Try again later or contact PRUONE service desk for assistance. ("
				+ hexErrorCode + ")";

		if ("301,306,307,308,703,704,705,706,708,803".indexOf(hexErrorCode) != -1
				&& hexErrorCode.length() == 3) {
			errorMessage = "Invalid Login ID/PIN. (" + hexErrorCode + ")";
		}
		if ("302,303,311,313,602,603,604,606,607,701,702".indexOf(hexErrorCode) != -1
				&& hexErrorCode.length() == 3) {
			errorMessage = "Invalid Login Credentials. (" + hexErrorCode + ")";
		}
		if ("801".indexOf(hexErrorCode) != -1 && hexErrorCode.length() == 3) {
			errorMessage = "Exceeded Maximum Password Change. (" + hexErrorCode
					+ ")";
		}
		if ("802".indexOf(hexErrorCode) != -1 && hexErrorCode.length() == 3) {
			errorMessage = "Password recently used. Please try another one. ("
					+ hexErrorCode + ")";
		}
		if ("707".indexOf(hexErrorCode) != -1 && hexErrorCode.length() == 3) {
			errorMessage = "Your PIN has expired, please click on 'Forgot PIN' now. ("
					+ hexErrorCode + ")";
		}
		if ("8,309,605,901,902,903,2011".indexOf(hexErrorCode) != -1
				&& hexErrorCode.length() == 3) {
			errorMessage = "Invalid request. Try again later. (" + hexErrorCode
					+ ")";
		}

		return errorMessage;
	}
}
