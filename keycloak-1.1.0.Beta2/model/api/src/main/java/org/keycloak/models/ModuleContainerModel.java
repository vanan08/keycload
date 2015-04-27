package org.keycloak.models;

import java.util.List;
import java.util.Map;

public interface ModuleContainerModel {
	
	ModuleModel getModuleById(String id);
	
	ModuleModel getModuleByName(String name);

	ModuleModel addModule(String name);

	ModuleModel addModule(String id, String name);

    boolean removeModule(ModuleModel module);

    List<ModuleModel> getModules();
    
    Map<String, ModuleModel> getModuleNameMap();
	
}
