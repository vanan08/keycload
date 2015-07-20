package org.keycloak.models.cache;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.keycloak.models.ModuleModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.cache.entities.CachedModule;

public class CacheModuleAdapter implements ModuleModel {

	private ModuleModel updated;
	private CachedModule cached;
	
	public CacheModuleAdapter(ModuleModel updated, CachedModule cached) {
		this.updated = updated;
		this.cached = cached;
	}
	
	@Override
	public void updateModule() {
		if (updated != null) updated.updateModule();
	}

	@Override
	public String getId() {
		if (updated != null) return updated.getId();
		return cached.getId();
	}

	@Override
	public String getName() {
		if (updated != null) return updated.getName();
		return cached.getName();
	}

	@Override
	public void setName(String name) {
		if (updated != null) updated.setName(name);
	}

	@Override
	public String getDescription() {
		if (updated != null) return updated.getDescription();
		return cached.getDescription();
	}

	@Override
	public void setDescription(String description) {
		if (updated != null) updated.setDescription(description);
	}

	@Override
	public String getUrl() {
		if (updated != null) return updated.getUrl();
		return cached.getUrl();
	}

	@Override
	public void setUrl(String url) {
		if (updated != null) updated.setUrl(url);
	}
	
//	@Override
//	public boolean hasScope(RoleModel role) {
//		if (updated != null) return updated.hasScope(role);
//		return false;
//	}

//	@Override
//	public RoleModel addRole(String rolename) {
//		return updated.addRole(rolename);
//	}

//	@Override
//	public void setRoles(String[] roles) {
//		if (updated != null) updated.setRoles(roles);
//	}

	@Override
	public boolean isActive() {
		if (updated != null) return updated.isActive();
		return cached.getActive();
	}

	@Override
	public void setActive(boolean active) {
		if (updated != null) updated.setActive(active);
	}
	
	@Override
	public boolean isExternalUrl() {
		if (updated != null) return updated.isExternalUrl();
		return cached.getExternalUrl();
	}

	@Override
	public void setExternalUrl(boolean externalUrl) {
		if (updated != null) updated.setExternalUrl(externalUrl);
	}

	@Override
	public Date getStartDate() {
		if (updated != null) return updated.getStartDate();
		return cached.getStartDate();
	}

	@Override
	public void setStartDate(Date startDate) {
		if (updated != null) updated.setStartDate(startDate);
	}

	@Override
	public Date getEndDate() {
		if (updated != null) return updated.getEndDate();
		return cached.getEndDate();
	}

	@Override
	public void setEndDate(Date endDate) {
		if (updated != null) updated.setEndDate(endDate);
	}

	@Override
	public String getCreatedBy() {
		if (updated != null) return updated.getCreatedBy();
		return cached.getCreatedBy();
	}

	@Override
	public void setCreatedBy(String createdBy) {
		if (updated != null) updated.setCreatedBy(createdBy);
	}

	@Override
	public Date getCreatedDate() {
		if (updated != null) return updated.getCreatedDate();
		return cached.getCreatedDate();
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		if (updated != null) updated.setCreatedDate(createdDate);
	}

	@Override
	public String getUpdatedBy() {
		if (updated != null) return updated.getUpdatedBy();
		return cached.getUpdatedBy();
	}

	@Override
	public void setUpdatedBy(String updatedBy) {
		if (updated != null) updated.setUpdatedBy(updatedBy);
	}

	@Override
	public Date getUpdatedDate() {
		if (updated != null) return updated.getUpdatedDate();
		return cached.getUpdatedDate();
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		if (updated != null) updated.setUpdatedDate(updatedDate);
	}

	@Override
	public List<String> getListRoles(String userId) {
		if (updated != null) return updated.getListRoles(userId);
        return cached.getRoles();
	}
	
	@Override
	public Set<RoleModel> getRoles(String userId) {
		return updated.getRoles(userId);
	}
	
//	@Override
//	public boolean container(RoleModel role) {
//		return updated.container(role);
//	}
	
//	@Override
//	public RoleModel getRole(String name) {
//		return updated.getRole(name);
//	}

//	@Override
//	public RoleModel addRole(String id, String name) {
//		return updated.addRole(id, name);
//	}

	@Override
	public boolean removeRole(String userId, RoleModel role) {
		return updated.removeRole(userId, role);
	}

	@Override
	public RoleModel addRole(String createdBy, String rolename) {
		return updated.addRole(createdBy, rolename);
	}

	@Override
	public boolean container(String userId, RoleModel role) {
		return updated.container(userId, role);
	}

	@Override
	public RoleModel getRoleByName(String userId, String name) {
		return updated.getRoleByName(userId, name);
	}

	@Override
	public boolean hasRole(String roleId) {
		return updated.hasRole(roleId);
	}

	@Override
	public boolean removeAllRoles() {
		return updated.removeAllRoles();
	}

	@Override
	public Set<RoleModel> getAllRoles() {
		return updated.getAllRoles();
	}

	@Override
	public RoleModel addRole(String rolename) {
		return updated.addRole(rolename);
	}

	@Override
	public void setRoles(List<String> roles) {
		updated.setRoles(roles);
	}

	@Override
	public void removeRole(String role) {
		updated.removeRole(role);
	}

	@Override
	public void removeRoles(List<String> roles) {
		updated.removeRoles(roles);
	}

	@Override
	public String getBaseUrl() {
		return updated.getBaseUrl();
	}

	@Override
	public String getFullpath() {
		return updated.getFullpath();
	}

}
