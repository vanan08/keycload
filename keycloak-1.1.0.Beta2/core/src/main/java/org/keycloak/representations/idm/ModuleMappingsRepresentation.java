package org.keycloak.representations.idm;

import java.util.List;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class ModuleMappingsRepresentation {
    protected String moduleId;
    protected String moduleName;
    protected String moduleDescription;

    protected List<RoleRepresentation> mappings;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public List<RoleRepresentation> getMappings() {
		return mappings;
	}

	public void setMappings(List<RoleRepresentation> mappings) {
		this.mappings = mappings;
	}

    
}
