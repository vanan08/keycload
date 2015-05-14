package org.keycloak.representations.idm;

import java.util.Arrays;

public class ModuleRepresentation {
	protected String id;
    protected String name;
    protected String url;
    protected String description;
    protected String [] roles;
    
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
	
	@Override
	public String toString() {
		return "{id="+id+",name="+name+",url="+url+",description="+description+",role="+Arrays.toString(roles)+"}";
	}
}
