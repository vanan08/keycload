package org.keycloak.models; 


public interface UserSubTypeModel {
    String getName();

    String getId();
    
    UserTypeModel getUserType();

    void setUserType(UserTypeModel userType);
    
    void setName(String name);

	public UserSubTypeContainerModel getContainer();

}
