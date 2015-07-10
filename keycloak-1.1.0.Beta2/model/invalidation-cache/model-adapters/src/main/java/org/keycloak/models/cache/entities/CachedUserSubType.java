package org.keycloak.models.cache.entities;

import org.keycloak.models.RealmModel;
import org.keycloak.models.UserSubTypeModel;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CachedUserSubType {
    final protected String id;
    final protected String name;
    final protected String realm;
    final protected String userType;

    public CachedUserSubType(UserSubTypeModel model, RealmModel realm) {
        id = model.getId();
        name = model.getName();
        userType = model.getUserType().getId();
        this.realm = realm.getId();
    }
    
    public String getUserType(){
    	return userType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRealm() {
        return realm;
    }

}
