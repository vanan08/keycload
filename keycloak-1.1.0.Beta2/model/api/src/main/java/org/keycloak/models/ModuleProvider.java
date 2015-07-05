package org.keycloak.models;

import org.keycloak.provider.Provider;

public interface ModuleProvider extends Provider {

	ModuleModel getModuleByName(String name);
	
	@Override
	public void close();
	
}
