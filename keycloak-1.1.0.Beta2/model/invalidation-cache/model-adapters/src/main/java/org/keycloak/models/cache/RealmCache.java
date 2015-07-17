package org.keycloak.models.cache;

import org.keycloak.models.cache.entities.CachedApplication;
import org.keycloak.models.cache.entities.CachedOAuthClient;
import org.keycloak.models.cache.entities.CachedRealm;
import org.keycloak.models.cache.entities.CachedRole;
import org.keycloak.models.cache.entities.CachedUserSubType;
import org.keycloak.models.cache.entities.CachedUserType;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface RealmCache {
    void clear();

    CachedRealm getCachedRealm(String id);

    void invalidateCachedRealm(CachedRealm realm);

    void addCachedRealm(CachedRealm realm);

    CachedRealm getCachedRealmByName(String name);

    void invalidateCachedRealmById(String id);

    CachedApplication getApplication(String id);

    void invalidateApplication(CachedApplication app);

    void addCachedApplication(CachedApplication app);

    void invalidateCachedApplicationById(String id);

    CachedOAuthClient getOAuthClient(String id);

    void invalidateOAuthClient(CachedOAuthClient client);

    void addCachedOAuthClient(CachedOAuthClient client);

    void invalidateCachedOAuthClientById(String id);

    CachedRole getRole(String id);

    void invalidateRole(CachedRole role);

    void addCachedRole(CachedRole role);


    void invalidateCachedRoleById(String id);


    void invalidateRoleById(String id);

    boolean isEnabled();

    void setEnabled(boolean enabled);
    
    // Kien Start cached for usersubtype
    void addCachedUserType(CachedUserType tncContent);

	void invalidateCachedUserTypeById(String id);

	void invalidateUserTypeById(String id);

	void invalidateUserType(CachedUserType userType);

	CachedUserType getUserType(String id);
    
    void addCachedUserSubType(CachedUserSubType userSubType);

	void invalidateCachedUserSubTypeById(String id);

	void invalidateUserSubTypeById(String id);

	void invalidateUserSubType(CachedUserSubType userSubType);

	CachedUserSubType getUserSubType(String id);
	// Kien End cached for usersubtype
}
