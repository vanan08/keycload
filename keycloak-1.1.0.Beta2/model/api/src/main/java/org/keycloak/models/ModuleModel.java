package org.keycloak.models;

import java.util.Date;
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
	
	boolean getActive();
	void setActive(boolean active);

	Date getStartDate();
	void setStartDate(Date startDate);
	
	Date getEndDate();
	void setEndDate(Date endDate);

	String getCreatedBy();
	void setCreatedBy(String createdBy);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	String getUpdatedBy();
	void setUpdatedBy(String updatedBy);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
    
    List<String> getListRoles(String userId);
    boolean hasRole(String roleId);
    Set<RoleModel> getAllRoles();
    
    RoleModel getRoleByName(String userId, String name);
    Set<RoleModel> getRoles(String userId);
    RoleModel addRole(String createdBy, String rolename);
    boolean removeRole(String userId, RoleModel role);
    boolean removeAllRoles();
    
    RoleModel addRole(String rolename);
    void setRoles(List<String> roles);
    void removeRole(String role);
    void removeRoles(List<String> roles);
    
    boolean container(String userId, RoleModel role);
    
    String getBaseUrl();
    public String getFullPath();
    
}
