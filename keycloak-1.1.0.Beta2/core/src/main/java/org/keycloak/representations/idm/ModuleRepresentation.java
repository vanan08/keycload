package org.keycloak.representations.idm;

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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String[] getRoles() {
		return roles;
	}
	
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "[id="+id+",name="+name+",url="+url+",description="+description+"]";
	}
}
