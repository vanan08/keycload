package org.keycloak.models.jpa.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ModuleEntity {

	@Id
    @Column(name="ID", length = 36)
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "URL")
	private String url;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID")
    private ApplicationEntity application;
	
    @OneToMany(fetch = FetchType.LAZY, cascade ={})
    @JoinTable(name="MODULE_ROLE", joinColumns = { @JoinColumn(name="MODULE_ID")}, inverseJoinColumns = { @JoinColumn(name="ROLE_ID")})
    Collection<RoleEntity> roles = new ArrayList<RoleEntity>();

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
	
	public ApplicationEntity getApplication() {
		return application;
	}
	
	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}
	
	public Collection<RoleEntity> getRoles() {
		return roles;
	}
	
	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}
	
	
}
