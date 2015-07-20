package org.keycloak.representations.idm;


public class UserTypeRepresentation {
	protected String id;
    protected String name;
    protected byte[] tncContent;
    
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
    
	public byte[] getTncContent() {
		return tncContent;
	}

	public void setTncContent(byte[] tncContent) {
		this.tncContent = tncContent;
	}

	@Override
    public String toString() {
        return name;
    }
}
