package org.keycloak.account;

import org.keycloak.events.Event;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.provider.Provider;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public interface AccountProvider extends Provider {

    AccountProvider setUriInfo(UriInfo uriInfo);

    Response createResponse(AccountPages page);

    AccountProvider setError(String message);

    AccountProvider setSuccess(String message);

    AccountProvider setWarning(String message);

    AccountProvider setUser(UserModel user);

    AccountProvider setProfileFormData(MultivaluedMap<String, String> formData);

    AccountProvider setStatus(Response.Status status);

    AccountProvider setRealm(RealmModel realm);

    AccountProvider setReferrer(String[] referrer);

    AccountProvider setEvents(List<Event> events);

    AccountProvider setSessions(List<UserSessionModel> sessions);

    AccountProvider setPasswordSet(boolean passwordSet);

    AccountProvider setStateChecker(String stateChecker);

    AccountProvider setFeatures(boolean social, boolean events, boolean passwordUpdateSupported);
}
