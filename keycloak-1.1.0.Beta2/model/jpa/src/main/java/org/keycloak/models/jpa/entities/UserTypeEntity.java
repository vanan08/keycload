package org.keycloak.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the custom_user_type database table.
 * 
 */
@Entity
@Table(name = "custom_user_type")
@NamedQuery(name = "getAllUserType", query = "SELECT c FROM UserTypeEntity c")
public class UserTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "custom_user_type_id")
	private String id;

	@Column(name = "accepted_tnc")
	private String acceptedTnc;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "tnc_content")
	private byte[] tncContent;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "user_type")
	private String name;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "customUserType")
	private List<UserTypeAppRoleEntity> customUserTypeAppRoles = new ArrayList<UserTypeAppRoleEntity>();

	public UserTypeEntity() {
	}
	
	public UserTypeEntity(String id) {
		this.id = id;
	}


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

	public String getAcceptedTnc() {
		return this.acceptedTnc;
	}

	public void setAcceptedTnc(String acceptedTnc) {
		this.acceptedTnc = acceptedTnc;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public byte[] getTncContent() {
		return this.tncContent;
	}

	public void setTncContent(byte[] tncContent) {
		this.tncContent = tncContent;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<UserTypeAppRoleEntity> getCustomUserTypeAppRoles() {
		return this.customUserTypeAppRoles;
	}

	public void setCustomUserTypeAppRoles(
			List<UserTypeAppRoleEntity> customUserTypeAppRoles) {
		this.customUserTypeAppRoles = customUserTypeAppRoles;
	}

	public UserTypeAppRoleEntity addCustomUserTypeAppRole(
			UserTypeAppRoleEntity customUserTypeAppRole) {
		getCustomUserTypeAppRoles().add(customUserTypeAppRole);
		customUserTypeAppRole.setCustomUserType(this);

		return customUserTypeAppRole;
	}

	public UserTypeAppRoleEntity removeCustomUserTypeAppRole(
			UserTypeAppRoleEntity customUserTypeAppRole) {
		getCustomUserTypeAppRoles().remove(customUserTypeAppRole);
		customUserTypeAppRole.setCustomUserType(null);

		return customUserTypeAppRole;
	}
}