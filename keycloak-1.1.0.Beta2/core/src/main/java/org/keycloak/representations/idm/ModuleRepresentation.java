package org.keycloak.representations.idm;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ModuleRepresentation {
	protected String id;
    protected String name;
    protected String url;
    protected String description;
    protected String [] roles;
    protected boolean isActive;
    protected String start_date;
    protected String end_date;
    protected String full_path;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		if (name == null) {
			return "";
		}
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		if (url == null) {
			return "";
		}
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getFullpath() {
		return full_path;
	}
	
	public void setFullpath(String full_path) {
		this.full_path = full_path;
	}

	public String getDescription() {
		if (description == null) {
			return "";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String[] getRoles() {
		if (roles == null) {
			return new String[]{};
		}
		return roles;
	}
	
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getStartdate() {
		return start_date;
	}

	public void setStartdate(String start_date) {
		this.start_date = start_date;
	}

	public String getEnddate() {
		return end_date;
	}
	
	public void setEnddate(String end_date) {
		this.end_date = end_date;
	}
	
	@Override
	public String toString() {
		return "{id="+id+",name="+name+",url="+url+",description="+description+"}";
	}
}
