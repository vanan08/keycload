package org.keycloak.services.resources.admin;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.models.utils.RepresentationToModel;
import org.keycloak.representations.idm.ModuleRepresentation;
import org.keycloak.services.resources.flows.Flows;

/**
 * Base resource for managing module
 * 
 */
public class ModuleResource {

	protected ModuleModel module;
	protected RealmModel realm;
    private RealmAuth auth;
    
    private final static Logger log = Logger.getLogger(ModuleResource.class);
	
	public ModuleResource(RealmModel realm, RealmAuth auth, ModuleModel module) {
		this.realm = realm;
		this.auth = auth;
		this.module = module;
		
		auth.init(RealmAuth.Resource.APPLICATION);
	}
	
	/**
     * Update the module.
     * @param rep
     * @return
     */
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(final ModuleRepresentation rep) {
        //auth.requireManage();

        try {
            RepresentationToModel.updateModule(realm, rep, module);
            return Response.noContent().build();
        } catch (ModelDuplicateException e) {
            return Flows.errors().exists("Module " + rep.getName() + " already exists");
        }
    }
	
	/**
     * Delete this module.
     *
     */
	@DELETE
    @NoCache
    public void deleteModule() {
        //auth.requireManage();
        // remove module
    }
	
	/**
	 * Get this module
	 * @return
	 */
	@GET
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public ModuleRepresentation getModule() {
//        auth.requireView();
		ModuleRepresentation repModule = ModelToRepresentation.toRepresentation(module);
		
		log.info(repModule.toString());
		
        return repModule;
    }
	
}
