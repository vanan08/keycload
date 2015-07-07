package org.keycloak.services.resources;

import javax.ws.rs.GET;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.ModuleModel;

public class PublicModuleResource {

	ModuleModel model;
	
	public PublicModuleResource(ModuleModel model) {
		this.model = model;
	}
	
	
	@GET
    @NoCache
	public String getModuleByName() {
		return model.getFullpath();
	}
	
}
