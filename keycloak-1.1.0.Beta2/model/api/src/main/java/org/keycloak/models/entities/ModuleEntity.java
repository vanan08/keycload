package org.keycloak.models.entities;

import java.util.ArrayList;
import java.util.List;

public class ModuleEntity extends AbstractIdentifiableEntity {

	private String name;
	private String description;
	private String url;
	private List<String> roleNames = new ArrayList<String>();
	private String applicationId;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<String> getRoleNames() {
		return roleNames;
	}
	
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	
	public String getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
}
