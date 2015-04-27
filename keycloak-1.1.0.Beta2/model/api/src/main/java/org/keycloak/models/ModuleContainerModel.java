package org.keycloak.models;

import java.util.Collection;
import java.util.Map;

public interface ModuleContainerModel {
	
	ModuleModel getModuleById(String id);
	
	ModuleModel getModuleByName(String name);

	ModuleModel addModule(String name);

	ModuleModel addModule(String id, String name);

    boolean removeModule(ModuleModel module);

    Collection<ModuleModel> getModules();
    
    Map<String, ModuleModel> getModuleNameMap();
	
}
