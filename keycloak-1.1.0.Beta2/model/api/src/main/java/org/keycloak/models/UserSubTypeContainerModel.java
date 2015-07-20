package org.keycloak.models;

import java.util.Set;

public interface UserSubTypeContainerModel {
	UserSubTypeModel getUserSubType(String name);
    
	UserSubTypeModel getUserSubTypeById(String id);

	UserSubTypeModel addUserSubType(String name);

	UserSubTypeModel addUserSubType(String id, String name);

    boolean removeUserSubType(UserSubTypeModel userSubType);

    Set<UserSubTypeModel> getUserSubTypes();
    
    boolean removeUserSubTypeById(String id);
    
    void refreshRealmUserSubTypesCache(String id, String name);
}
