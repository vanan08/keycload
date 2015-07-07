package org.keycloak.representations.idm;

public class ModuleRepresentation {
	protected String id;
    protected String name;
    protected String url;
    protected String description;
    protected String [] roles;
    protected boolean active;
    protected String startdate;
    protected String enddate;
    protected String fullpath;
    
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
		return fullpath;
	}
	
	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
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
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}
	
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	@Override
	public String toString() {
		return "{id="+id+",name="+name+",url="+url+",description="+description+"}";
	}
}
