package org.keycloak.models.jpa;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.keycloak.models.ModuleModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.jpa.entities.ApplicationEntity;
import org.keycloak.models.jpa.entities.ModuleEntity;

public class ApplicationModuleAdapter implements ModuleModel {

	private ModuleEntity moduleEntity;
	private ApplicationInfo applicationInfo;
	
	public ApplicationModuleAdapter(ModuleEntity moduleEntity, ApplicationInfo applicationInfo) {
		this.moduleEntity = moduleEntity;
		this.applicationInfo = applicationInfo;
	}
	
	@Override
	public String getBaseUrl() {
		return applicationInfo.getBaseUrl();
	}
	
	@Override
	public String getFullPath() {
		String slash = "/";
		if (getBaseUrl().indexOf(slash, getBaseUrl().length() - 1) > -1) {
			return getBaseUrl() + getUrl();
		} else {
			return getBaseUrl() + slash + getUrl();
		}
	}
	
	@Override
	public void updateModule() {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public String getId() {
		return moduleEntity.getId();
	}

	@Override
	public String getName() {
		return moduleEntity.getName();
	}

	@Override
	public void setName(String name) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public String getDescription() {
		return moduleEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public String getUrl() {
		return moduleEntity.getUrl();
	}

	@Override
	public void setUrl(String url) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public boolean isActive() {
		return moduleEntity.isActive();
	}

	@Override
	public void setActive(boolean active) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public Date getStartDate() {
		return moduleEntity.getStartDate();
	}

	@Override
	public void setStartDate(Date startDate) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public Date getEndDate() {
		return moduleEntity.getEndDate();
	}

	@Override
	public void setEndDate(Date endDate) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public String getCreatedBy() {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public void setCreatedBy(String createdBy) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public Date getCreatedDate() {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public String getUpdatedBy() {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public void setUpdatedBy(String updatedBy) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public Date getUpdatedDate() {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public List<String> getListRoles(String userId) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public boolean hasRole(String roleId) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public Set<RoleModel> getAllRoles() {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public RoleModel getRoleByName(String userId, String name) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public Set<RoleModel> getRoles(String userId) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public RoleModel addRole(String createdBy, String rolename) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public boolean removeRole(String userId, RoleModel role) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public boolean removeAllRoles() {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public RoleModel addRole(String rolename) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public void setRoles(List<String> roles) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public void removeRole(String role) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public void removeRoles(List<String> roles) {
		throw new UnsupportedOperationException("not support this method");
	}

	@Override
	public boolean container(String userId, RoleModel role) {
		throw new UnsupportedOperationException("not support this method");
	}
	
	public static class ApplicationInfo {
		
		private ApplicationEntity applicationEntity;
		
		public ApplicationInfo(ApplicationEntity applicationEntity) {
			this.applicationEntity = applicationEntity;
		}
		
		public String getName() {
			return applicationEntity.getName();
		}
		
		public String getBaseUrl() {
			return applicationEntity.getBaseUrl();
		}
		
	}

}
