package org.keycloak.models.jpa;  

import javax.persistence.EntityManager;

import org.keycloak.models.RealmModel;
import org.keycloak.models.UserSubTypeContainerModel;
import org.keycloak.models.UserSubTypeModel;
import org.keycloak.models.UserTypeModel;
import org.keycloak.models.jpa.entities.UserSubTypeEntity;
import org.keycloak.models.jpa.entities.UserTypeEntity;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UserSubTypeAdapter implements UserSubTypeModel {
	protected UserSubTypeEntity userSubType;
	protected EntityManager em;
	protected RealmModel realm;

	public UserSubTypeAdapter(RealmModel realm, EntityManager em,
			UserSubTypeEntity userSubType) {
		this.em = em;
		this.realm = realm;
		this.userSubType = userSubType;
	}

	public UserSubTypeEntity getUserSubType() {
		return userSubType;
	}

	public void setUserSubType(UserSubTypeEntity userSubType) {
		this.userSubType = userSubType;
	}

	@Override
	public String getId() {
		return userSubType.getId();
	}

	@Override
	public String getName() {
		return userSubType.getName();
	}

	@Override
	public void setName(String name) {
		System.out.println("#### jpa setName: " + name);
		userSubType.setName(name);
	}

	@Override
	public UserTypeModel getUserType() {
//		if (userSubType.getUserType() == null)
//			return "";
		return new UserTypeAdapter(realm, em, userSubType.getUserType());
	}

	@Override
	public void setUserType(UserTypeModel userType) {
		System.out.println("#### set usertype: " + userType);
		//TODO: get real user type from DB
		UserTypeEntity entity = UserTypeAdapter.toUserTypeEntity(userType, em);
		userSubType.setUserType(entity);
	}

	@Override
	public UserSubTypeContainerModel getContainer() {
		return realm;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof UserSubTypeModel))
			return false;

		UserSubTypeModel that = (UserSubTypeModel) o;
		return that.getId().equals(getId());
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	public static UserSubTypeEntity toUserSubTypeEntity(UserSubTypeModel model,
			EntityManager em) {
		if (model instanceof UserSubTypeAdapter) {
			return ((UserSubTypeAdapter) model).getUserSubType();
		}
		return em.getReference(UserSubTypeEntity.class, model.getId());
	}

}
