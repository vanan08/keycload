package org.keycloak.models;

import java.util.List;
import java.util.Map;

public interface ModuleContainerModel {
	
	ModuleModel getModuleById(String id);
	
	ModuleModel getModuleByName(String name);

	ModuleModel getModuleByRedirectUrl(String url);
	
	public ModuleModel addModule(String name, String url);

	public ModuleModel addModule(String id, String name, String url);

    boolean removeModule(ModuleModel module);
    
    boolean container(ModuleModel module);

    List<ModuleModel> getModules();
    
    Map<String, ModuleModel> getModuleNameMap();
	
}
