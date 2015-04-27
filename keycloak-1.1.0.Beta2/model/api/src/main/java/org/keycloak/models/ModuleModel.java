package org.keycloak.models;

import java.util.List;

public interface ModuleModel {

	void updateModule();

	String getId();
	
    String getName();
    void setName(String name);
    
    String getDescription();
    void setDescription(String description);
    
	String getUrl();
	void setUrl(String url);
	
	boolean hasScope(RoleModel role);
    
    void addRole(String rolename);
    void setRoles(String [] roles);
    List<String> getListRoles();
    
}
