package org.keycloak.models.cache.entities;

import org.keycloak.models.RealmModel;
import org.keycloak.models.UserTypeModel;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CachedUserType {
    final protected String id;
    final protected String name;
    final protected String realm;
    final protected String tncContent;

    public CachedUserType(UserTypeModel model, RealmModel realm) {
        id = model.getId();
        name = model.getName();
        tncContent = model.getTncContent();
        this.realm = realm.getId();
    }
    
    public String getTncContent() {
		return tncContent;
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
