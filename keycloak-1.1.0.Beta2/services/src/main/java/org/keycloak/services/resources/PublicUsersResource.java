package org.keycloak.services.resources;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.jboss.logging.Logger;
import org.keycloak.models.KeycloakSession;

@Path("/users")
public class PublicUsersResource {

	private static Logger logger = Logger.getLogger(PublicModulesResource.class);
	
	@Context
    protected KeycloakSession session;
	
}
