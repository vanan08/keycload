package org.keycloak.services.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.ModuleRepresentation;
import org.keycloak.services.managers.RealmManager;

import com.client.ClientAPI;
import com.client.ClientNoAccessException;
import com.client.NoServerException;
import com.client.TokenOTIP;
import com.og.smssender.SMSSend;

@Path("/modules")
public class PublicModulesResource {

	private static final Logger logger = Logger.getLogger(PublicModulesResource.class);
	
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

	@Context
	protected KeycloakSession session;

	@GET
	@NoCache
	@Produces("application/json")
	public String helloworld() {
		return "heloworld";
	}

	@Path("{username}/{mobile}")
	@GET
	@NoCache
	@Produces("application/json")
	public int sendSMS(final @PathParam("username") String username,
			final @PathParam("mobile") String mobile) {
		ClientAPI clientAPI = null;
		int domain = 0;
		int mediaType = 19;
		String propertiesPath;
		int result = -1;
		try {
			domain = Integer.parseInt(getPropAuthenticationValues("domain"));
			propertiesPath = getPropAuthenticationValues("propertiesPath");
			System.out.println("KeyCloack protertiesPath " + propertiesPath);
			
			clientAPI = initClientAPI();
			TokenOTIP tokenOTIP = clientAPI.getTokenOTIP(EMPTY_BYTES, username,
					domain, SERIAL_NO, mediaType);

			String[] otips = new String[1];
			otips = tokenOTIP.getOTIPs();

			System.out.println("KeyCloack:OTP code get from HSM=" + otips[0]);

			String msg = "OTP: " + otips[0];
			// send out the token to user's mobile

			SMSSend smsSend = new SMSSend(propertiesPath);

			System.out.println("KeyCloack protertiesPath " + propertiesPath);

			result = smsSend.sendSMS(mobile, msg, smsSend.ENCODING_ASCII, MOBILE_CODE);
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	@Path("{name}")
	@GET
	@NoCache
	public String getPublicModuleResource(final @PathParam("name") String name) {
		RealmManager moduleManager = new RealmManager(session);
		ModuleModel module = moduleManager.getModuleByName(name);
		if (module == null) {
			return null;
		}
		return module.getFullpath();
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
	
	@Path("{name}/info")
	@GET
    @NoCache
    @Produces("application/json")
	public ModuleRepresentation getModule(final @PathParam("name") String name) {
		RealmManager moduleManager = new RealmManager(session);
		ModuleModel module = moduleManager.getModuleByName(name);
		if (module == null) {
			return null;
		}
		
		return ModelToRepresentation.toRepresentation(module);
	}
}
