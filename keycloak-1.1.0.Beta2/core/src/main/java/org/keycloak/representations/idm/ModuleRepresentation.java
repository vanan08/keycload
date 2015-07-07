package org.keycloak.representations.idm;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleRepresentation {
	protected String id;
    protected String name;
    protected String url;
    protected String description;
    protected String [] roles;
    protected boolean active;
    protected String startdate;
    protected String enddate;
    protected String fullPath;
    
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
	
	public String getFullPath() {
		return fullPath;
	}
	
	@JsonProperty("FullPath")
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
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
	
	@JsonProperty("IsActive")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStartdate() {
		return startdate;
	}

	@JsonProperty("Startdate")
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	@JsonProperty("Enddate")
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	@Override
	public String toString() {
		return "{id="+id+",name="+name+",url="+url+",description="+description+"}";
	}
}
