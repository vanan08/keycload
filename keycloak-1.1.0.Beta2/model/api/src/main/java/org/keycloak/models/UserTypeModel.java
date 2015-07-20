package org.keycloak.models;


public interface UserTypeModel {
    String getName();

    String getId();
    
    byte[] getTncContent();
    
    void setName(String name);
    
    void setTncContent(byte[] tncContent);
    
    String getUserTypeRole();
    
    void setUserTypeRole(String userTypeRole);
    
	public UserTypeContainerModel getContainer();

}
