package org.keycloak.models;


public interface UserTypeModel {
    String getName();

    String getId();
    
    byte[] getTncContent();
    
    void setName(String name);
    
    void setTncContent(byte[] tncContent);
    
	public UserTypeContainerModel getContainer();

}
