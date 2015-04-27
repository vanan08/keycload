package org.keycloak.representations.idm;

import java.util.List;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class ApplicationMappingsRepresentation {
    protected String applicationId;
    protected String application;

    protected List<RoleRepresentation> mappings;
    protected List<ModuleRepresentation> modules;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public List<RoleRepresentation> getMappings() {
        return mappings;
    }

    public void setMappings(List<RoleRepresentation> mappings) {
        this.mappings = mappings;
    }

    public List<ModuleRepresentation> getModules() {
		return modules;
	}

	public void setModuleMappings(List<ModuleRepresentation> modules) {
		this.modules = modules;
	}
    
}
