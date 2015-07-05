package org.keycloak.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModuleModel;
import org.keycloak.services.managers.RealmManager;

@Path("/modules")
public class PublicModulesResource {

	@Context
    protected KeycloakSession session;
	
	@GET
    @NoCache
    @Produces("application/json")
	public String helloworld() {
		return "heloworld";
	}
	
	@Path("{name}")
	@GET
    @NoCache
	public String getPublicModuleResource(final @PathParam("name") String name) {
		RealmManager moduleManager = new RealmManager(session);
		ModuleModel module = moduleManager.getModuleByName(name);
		if (module == null) {
			return null;
		}
		return module.getFullPath();
	}
	
}
