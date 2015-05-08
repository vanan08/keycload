package org.keycloak.models;

import java.util.List;
import java.util.Set;

public interface ModuleModel {

	void updateModule();
	
	String getId();
	
    String getName();
    void setName(String name);
    
    String getDescription();
    void setDescription(String description);
    
	String getUrl();
	void setUrl(String url);
    
    List<String> getListRoles(String userId);
    boolean hasRole(String roleId);
    
    RoleModel getRoleByName(String userId, String name);
    Set<RoleModel> getRoles(String userId);
    RoleModel addRole(String userId, String rolename);
    boolean removeRole(String userId, RoleModel role);
    
    boolean container(String userId, RoleModel role);
    
}
