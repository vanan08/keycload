package org.keycloak.models.cache.entities;

import java.util.List;

public class CachedModule {

	final protected String id;
    final protected String name;
    final protected String url;
    final protected String description;
    final protected List<String> roles;

    public CachedModule(String id, String name, String url, String description, List<String> roles) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.description = description;
		this.roles = roles;
	}
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
		return url;
	}
    
    public String getDescription() {
        return description;
    }
    
    public List<String> getRoles() {
		return roles;
	}
    
}
