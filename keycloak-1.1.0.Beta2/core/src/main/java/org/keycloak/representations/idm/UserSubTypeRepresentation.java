package org.keycloak.representations.idm;


public class UserSubTypeRepresentation {
	protected String id;
    protected String name;
    protected String userType;
    
    public String getUserType(){
    	return userType;
    }

    public void setUserType(String userType){
    	this.userType = userType;
    }

    public UserSubTypeRepresentation() {
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

    @Override
    public String toString() {
        return name;
    }
}
