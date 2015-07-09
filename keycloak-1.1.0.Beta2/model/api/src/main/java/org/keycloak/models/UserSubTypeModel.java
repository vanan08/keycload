package org.keycloak.models;


public interface UserSubTypeModel {
    String getName();

    String getId();
    
    String getUserType();

    void setUserType(String userType);
    
    void setName(String name);

	public UserSubTypeContainerModel getContainer();

}
