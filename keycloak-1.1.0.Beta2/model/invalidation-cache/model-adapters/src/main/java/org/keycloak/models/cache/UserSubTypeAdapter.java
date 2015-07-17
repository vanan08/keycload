package org.keycloak.models.cache; 

import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserSubTypeContainerModel;
import org.keycloak.models.UserSubTypeModel;
import org.keycloak.models.UserTypeModel;
import org.keycloak.models.cache.entities.CachedUserSubType;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UserSubTypeAdapter implements UserSubTypeModel {

    protected UserSubTypeModel updated;
    protected CachedUserSubType cached;
    protected RealmCache cache;
    protected CacheRealmProvider cacheSession;
    protected RealmModel realm;

    public UserSubTypeAdapter(CachedUserSubType cached, RealmCache cache, CacheRealmProvider session, RealmModel realm) {
        this.cached = cached;
        this.cache = cache;
        this.cacheSession = session;
        this.realm = realm;
    }

    protected void getDelegateForUpdate() {
        if (updated == null) {
            cacheSession.registerUserSubTypeInvalidation(getId());
            updated = cacheSession.getDelegate().getUserSubTypeById(getId(), realm);
            if (updated == null) throw new IllegalStateException("Not found in database");
        }
    }


    @Override
    public String getName() {
        if (updated != null) return updated.getName();
        return cached.getName();
    }

    @Override
    public void setName(String name) {
    	System.out.println("###### cache setName : " + name);
        getDelegateForUpdate();
        updated.setName(name);
        realm.refreshRealmUserSubTypesCache(getId(), name);
    }
    
	@Override
	public UserTypeModel getUserType(){
		if (updated != null) return updated.getUserType();
		UserTypeModel userType = realm.getUserTypeById(cached.getUserType());
        if (userType == null) {
            throw new IllegalStateException("Could not find User Type: " + cached.getUserType());
        }
        return userType;
	}

	@Override
	public void setUserType(UserTypeModel userType) {
		System.out.println("######cached: " + getUserType());
		System.out.println("#########new: " + userType);
		if(!userType.getId().equals(getUserType().getId())){
			System.out.println("######### gotta update ");
			getDelegateForUpdate();
			updated.setUserType(userType);
		}
	}
	
    @Override
    public String getId() {
        if (updated != null) return updated.getId();
        return cached.getId();
    }

    @Override
    public UserSubTypeContainerModel getContainer() {
        return realm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserSubTypeModel)) return false;

        UserSubTypeModel that = (UserSubTypeModel) o;
        return that.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
