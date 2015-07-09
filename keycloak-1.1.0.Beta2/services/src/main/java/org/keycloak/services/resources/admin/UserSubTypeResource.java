package org.keycloak.services.resources.admin;

import org.jboss.resteasy.spi.NotFoundException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserSubTypeModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserSubTypeRepresentation;

public abstract class UserSubTypeResource {

	protected RealmModel realm;
	
	public UserSubTypeResource(RealmModel realm) {
        this.realm = realm;
    }

    protected UserSubTypeRepresentation getUserSubType(UserSubTypeModel userSubTypeModel) {
        return ModelToRepresentation.toRepresentation(userSubTypeModel);
    }

    protected void deleteUserSubType(UserSubTypeModel userSubType) {
        if (!userSubType.getContainer().removeUserSubType(userSubType)) {
            throw new NotFoundException("UserSubType not found");
        }
    }

    protected void updateUserSubType(UserSubTypeRepresentation rep, UserSubTypeModel userSubTypeModel) {
    	userSubTypeModel.setName(rep.getName());
    	userSubTypeModel.setUserType(rep.getUserType());
    }
    
}
