package org.keycloak.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the custom_user_subtype database table.
 * 
 */
@Entity
@Table(name = "CUSTOM_USER_SUBTYPE")
@NamedQueries({
		@NamedQuery(name = "getAllUserSubType", query = "SELECT c FROM UserSubTypeEntity c"),
		@NamedQuery(name = "getUserSubTypeById", query = "SELECT c FROM UserSubTypeEntity c WHERE c.id = :id "),
		@NamedQuery(name = "getUserSubTypeByName", query = "SELECT c FROM UserSubTypeEntity c WHERE c.name = :name ") })
public class UserSubTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CUSTOM_USER_SUBTYPE_ID")
	private String id;

	@Column(name = "USER_SUB_TYPE")
	private String name;

	@Column(name = "CUSTOM_USER_TYPE_ID", nullable = true)
	private String userType;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

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

	public UserSubTypeEntity() {
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

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}