package org.keycloak.services.managers;

import org.jboss.logging.Logger;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.ModuleProvider;

public class ModuleManager {

	protected static final Logger logger = Logger.getLogger(ModuleManager.class);

    protected KeycloakSession session;
    protected ModuleProvider model;
    
    public ModuleManager(KeycloakSession session) {
		this.session = session;
		this.model = session.modules();
	}
    
    public ModuleModel getModuleByName(String name) {
    	return model.getModuleByName(name);
    }
	
}
