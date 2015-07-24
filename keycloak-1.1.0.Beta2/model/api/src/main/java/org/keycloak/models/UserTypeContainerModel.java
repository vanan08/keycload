package org.keycloak.models;

import java.util.List;
import java.util.Set;

public interface UserTypeContainerModel {
	UserTypeModel getUserType(String name);

	UserTypeModel getUserTypeById(String id);

	UserTypeModel addUserType(String name);

	UserTypeModel addUserType(String id, String name);

	boolean removeUserType(UserTypeModel userType);

	Set<UserTypeModel> getUserTypes();

	boolean removeUserTypeById(String id);

	void refreshRealmUserTypesCache(String id, String name);

	List<UserTypeModel> getUserTypes(RealmModel realm, String search, int firstResult,
			int maxResults);
}
