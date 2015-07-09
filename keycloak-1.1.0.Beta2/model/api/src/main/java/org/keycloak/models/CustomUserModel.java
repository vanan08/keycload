package org.keycloak.models;

import java.sql.Timestamp;

public interface CustomUserModel {

	String getId();
	void setId(String id);
	
	String getAcceptedTNC();
	void setAcceptedTNC(String acceptedTNC);
	
	Timestamp getAcceptedTNCdatetime();
	void setAcceptedTNCdatetime(Timestamp acceptedTNCdatetime);

	String getCreatedby();
	void setCreatedby(String createdby);
	
	Timestamp getCreateddate();
	void setCreateddate(Timestamp createddate);
	
	String getUpdatedby();
	void setUpdateby(String updatedby);
	
	Timestamp getUpdateddate();
	void setUpdateddate(Timestamp updateddate);
	
	void updateCustomUser();
	
}
