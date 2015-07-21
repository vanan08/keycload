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
	public String getFullpath() {
		String slash = "/";
		if(!moduleEntity.isExternalUrl()){
			if (getBaseUrl().indexOf(slash, getBaseUrl().length() - 1) > -1) {
				return getBaseUrl() + getUrl();
			} else {
				return getBaseUrl() + slash + getUrl();
			}
		}else{
			return getUrl();
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
	public boolean isExternalUrl() {
		return moduleEntity.isExternalUrl();
	}

	@Override
	public void setExternalUrl(boolean externalUrl) {
		moduleEntity.setExternalUrl(externalUrl);
	}

	@Override
	public void setActive(boolean active) {
		moduleEntity.setActive(active);
	}

	@Override
	public Date getStartDate() {
		return moduleEntity.getStartDate();
	}

	@Override
	public void setStartDate(Date startDate) {
		moduleEntity.setStartDate(startDate);
	}

	@Override
	public Date getEndDate() {
		return moduleEntity.getEndDate();
	}

	@Override
	public void setEndDate(Date endDate) {
		moduleEntity.setEndDate(endDate);
	}

	@Override
	public String getCreatedBy() {
		return moduleEntity.getCreatedBy();
	}

	@Override
	public void setCreatedBy(String createdBy) {
		moduleEntity.setCreatedBy(createdBy);
	}

	@Override
	public Date getCreatedDate() {
		return moduleEntity.getCreatedDate();
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		moduleEntity.setCreatedDate(createdDate);
	}

	@Override
	public String getUpdatedBy() {
		return moduleEntity.getUpdatedBy();
	}

	@Override
	public void setUpdatedBy(String updatedBy) {
		moduleEntity.setUpdatedBy(updatedBy);
	}

	@Override
	public Date getUpdatedDate() {
		return moduleEntity.getUpdatedDate();
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		moduleEntity.setUpdatedDate(updatedDate);
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
