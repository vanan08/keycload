package org.keycloak.models.jpa;

import java.sql.Timestamp;

import org.keycloak.models.CustomUserModel;
import org.keycloak.models.jpa.entities.CustomUserEntity;

public class CustomUserAdapter implements CustomUserModel {

	protected CustomUserEntity customUserEntity;
	
	public CustomUserAdapter(CustomUserEntity customUserEntity) {
		this.customUserEntity = customUserEntity;
	}
	
	@Override
	public String getId() {
		return customUserEntity.getId();
	}
	
	@Override
	public void setId(String id) {
		customUserEntity.setId(id);
	}
	
	@Override
	public String getAcceptedTNC() {
		return customUserEntity.getAcceptedTNC();
	}

	@Override
	public void setAcceptedTNC(String acceptedTNC) {
		customUserEntity.setAcceptedTNC(acceptedTNC);
	}

	@Override
	public Timestamp getAcceptedTNCdatetime() {
		return customUserEntity.getAcceptedTNCdatetime();
	}

	@Override
	public void setAcceptedTNCdatetime(Timestamp acceptedTNCdatetime) {
		customUserEntity.setAcceptedTNCdatetime(acceptedTNCdatetime);
	}

	@Override
	public String getCreatedby() {
		return customUserEntity.getCreatedby();
	}

	@Override
	public void setCreatedby(String createdby) {
		customUserEntity.setCreatedby(createdby);
	}

	@Override
	public Timestamp getCreateddate() {
		return customUserEntity.getCreateddate();
	}

	@Override
	public void setCreateddate(Timestamp createddate) {
		customUserEntity.setCreateddate(createddate);
	}

	@Override
	public String getUpdatedby() {
		return customUserEntity.getUpdatedby();
	}

	@Override
	public void setUpdateby(String updatedby) {
		customUserEntity.setUpdatedby(updatedby);
	}

	@Override
	public Timestamp getUpdateddate() {
		return customUserEntity.getUpdateddate();
	}

	@Override
	public void setUpdateddate(Timestamp updateddate) {
		customUserEntity.setUpdateddate(updateddate);
	}

}
