package org.keycloak.models.jpa.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*Test HieuHN*/
@Entity
@Table(name = "CUSTOM_USER")
public class CustomUserEntity {

	@Id
    @Column(name="CUSTOM_USER_ID", length = 36)
	private String id;
	//nahung test
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
	private UserEntity user;
	
	@Column(name = "ACCEPTED_TNC", length = 1)
	private String acceptedTNC;
	
	@Column(name = "ACCEPTED_TNC_DATETIME")
	private Timestamp acceptedTNCdatetime;
	
	@Column(name = "CREATED_BY")
	private String createdby;
	
	@Column(name = "CREATED_DATE")
	private Timestamp createddate;
	
	@Column(name = "UPDATED_BY")
	private String updatedby;
	
	@Column(name = "UPDATED_DATE")
	private Timestamp updateddate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getAcceptedTNC() {
		return acceptedTNC;
	}

	public void setAcceptedTNC(String acceptedTNC) {
		this.acceptedTNC = acceptedTNC;
	}

	public Timestamp getAcceptedTNCdatetime() {
		return acceptedTNCdatetime;
	}

	public void setAcceptedTNCdatetime(Timestamp acceptedTNCdatetime) {
		this.acceptedTNCdatetime = acceptedTNCdatetime;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Timestamp getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}
	
}
