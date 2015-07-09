package org.keycloak.services.resources.admin;

import org.jboss.resteasy.spi.NotFoundException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserTypeModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserTypeRepresentation;

public abstract class UserTypeResource {

	protected RealmModel realm;
	
	public UserTypeResource(RealmModel realm) {
        this.realm = realm;
    }

    protected UserTypeRepresentation getUserType(UserTypeModel userTypeModel) {
        return ModelToRepresentation.toRepresentation(userTypeModel);
    }

    protected void deleteUserType(UserTypeModel userType) {
        if (!userType.getContainer().removeUserType(userType)) {
            throw new NotFoundException("UserSubType not found");
        }
    }

    protected void updateUserType(UserTypeRepresentation rep, UserTypeModel userTypeModel) {
    	userTypeModel.setName(rep.getName());
    	userTypeModel.setTncContent(rep.getTncContent());
    }
    
}
