package org.keycloak.representations.idm;


public class UserTypeRepresentation {
	protected String id;
    protected String name;
    protected String tncContent;
    
    public UserTypeRepresentation() {
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
    
	public String getTncContent() {
		return tncContent;
	}

	public void setTncContent(String tncContent) {
		this.tncContent = tncContent;
	}

	@Override
    public String toString() {
        return name;
    }
}
