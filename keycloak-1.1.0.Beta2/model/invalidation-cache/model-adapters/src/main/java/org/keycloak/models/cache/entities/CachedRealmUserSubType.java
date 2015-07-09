package org.keycloak.models.cache.entities;

import org.keycloak.models.RealmModel;
import org.keycloak.models.UserSubTypeModel;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CachedRealmUserSubType extends CachedUserSubType {


    public CachedRealmUserSubType(UserSubTypeModel model, RealmModel realm) {
        super(model, realm);

    }

}
