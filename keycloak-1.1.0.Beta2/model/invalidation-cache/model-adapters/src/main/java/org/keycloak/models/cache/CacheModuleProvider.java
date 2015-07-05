package org.keycloak.models.cache;

import org.keycloak.models.ModuleModel;
import org.keycloak.models.ModuleProvider;

public interface CacheModuleProvider extends ModuleProvider {

	ModuleProvider getDelegate();
	
	ModuleModel getModuleByName(String name);
	
}
