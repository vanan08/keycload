package org.keycloak.models.jpa;

import javax.persistence.EntityManager;

import org.keycloak.models.RealmModel;
import org.keycloak.models.UserTypeContainerModel;
import org.keycloak.models.UserTypeModel;
import org.keycloak.models.jpa.entities.UserTypeEntity;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UserTypeAdapter implements UserTypeModel {
	protected UserTypeEntity userType;
	protected EntityManager em;
	protected RealmModel realm;

	public UserTypeAdapter(RealmModel realm, EntityManager em,
			UserTypeEntity userType) {
		this.em = em;
		this.realm = realm;
		this.userType = userType;
	}

	public UserTypeEntity getUserType() {
		return userType;
	}
	
	@Override
	public String getId() {
		return userType.getId();
	}

	@Override
	public String getName() {
		return userType.getName();
	}

	@Override
	public void setName(String name) {
		userType.setName(name);
	}

	@Override
	public byte[] getTncContent() {
		return userType.getTncContent();
	}
	
	@Override
	public String getUserTypeRole() {
		return userType.getUserTypeRole();
	}
	
	@Override
	public void setTncContent(byte[] tncContent) {
		userType.setTncContent(tncContent);
	}
	
	@Override
	public void setUserTypeRole(String userTypeRole) {
		userType.setUserTypeRole(userTypeRole);
		
	}

	@Override
	public UserTypeContainerModel getContainer() {
		return realm;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof UserTypeModel))
			return false;

		UserTypeModel that = (UserTypeModel) o;
		return that.getId().equals(getId());
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	public static UserTypeEntity toUserTypeEntity(UserTypeModel model,
			EntityManager em) {
		if (model instanceof UserTypeAdapter) {
			return ((UserTypeAdapter) model).getUserType();
		}
		return em.getReference(UserTypeEntity.class, model.getId());
	}

}
