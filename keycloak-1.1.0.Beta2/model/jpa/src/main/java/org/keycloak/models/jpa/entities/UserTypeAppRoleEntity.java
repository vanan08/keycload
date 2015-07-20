package org.keycloak.models.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the custom_user_type_app_role database table.
 * 
 */
@Entity
@Table(name="custom_user_type_app_role", schema="public")
@NamedQuery(name="getAllUserTypeAppRole", query="SELECT c FROM UserTypeAppRoleEntity c")
public class UserTypeAppRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private String customUserTypeAppRoleId;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="role_id")
	private String roleId;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@ManyToOne
	@JoinColumn(name="custom_user_type_id")
	private UserTypeEntity customUserType;

	public UserTypeAppRoleEntity() {
	}

	public String getCustomUserTypeAppRoleId() {
		return this.customUserTypeAppRoleId;
	}

	public void setCustomUserTypeAppRoleId(String customUserTypeAppRoleId) {
		this.customUserTypeAppRoleId = customUserTypeAppRoleId;
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

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public UserTypeEntity getCustomUserType() {
		return this.customUserType;
	}

	public void setCustomUserType(UserTypeEntity customUserType) {
		this.customUserType = customUserType;
	}

}