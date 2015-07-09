package org.keycloak.models;


public interface UserTypeModel {
    String getName();

    String getId();
    
    String getTncContent();
    
    void setName(String name);
    
    void setTncContent(String tncContent);
    
	public UserTypeContainerModel getContainer();

}
