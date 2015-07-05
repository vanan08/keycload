package org.keycloak.models.jpa;

import javax.persistence.EntityManager;

import org.keycloak.Config;
import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModuleProvider;
import org.keycloak.models.ModuleProviderFactory;

public class JpaModuleProviderFactory implements ModuleProviderFactory {

	@Override
	public ModuleProvider create(KeycloakSession session) {
		EntityManager em = session.getProvider(JpaConnectionProvider.class).getEntityManager();
		return new JpaModuleProvider(em);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void init(Config.Scope config) {
    }

    @Override
    public String getId() {
        return "jpa";
    }

}
