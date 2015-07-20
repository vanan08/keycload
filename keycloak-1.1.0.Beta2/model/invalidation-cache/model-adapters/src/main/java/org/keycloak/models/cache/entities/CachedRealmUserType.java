package org.keycloak.models.cache.entities;

import org.keycloak.models.RealmModel;
import org.keycloak.models.UserTypeModel;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CachedRealmUserType extends CachedUserType {


    public CachedRealmUserType(UserTypeModel model, RealmModel realm) {
        super(model, realm);

    }

}
