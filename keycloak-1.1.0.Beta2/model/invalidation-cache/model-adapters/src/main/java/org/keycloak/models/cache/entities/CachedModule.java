package org.keycloak.models.cache.entities;

import java.util.Date;
import java.util.List;

public class CachedModule {

	final protected String id;
    final protected String name;
    final protected String url;
    final protected String description;
    final protected List<String> roles;
    final protected String active;
    final protected Date startDate;
    final protected Date endDate;
    final protected String createdBy;
	final protected Date createdDate;
	final protected String updatedBy;
	final protected Date updatedDate;

    public CachedModule(String id, String name, String url, String description, List<String> roles,
    		String active, Date startDate, Date endDate, String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.description = description;
		this.roles = roles;
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
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

	public String getActive() {
		return active;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}
    
}
