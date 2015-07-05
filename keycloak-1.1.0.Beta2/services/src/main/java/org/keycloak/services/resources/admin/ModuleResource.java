package org.keycloak.services.resources.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.ApplicationModel;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.models.utils.RepresentationToModel;
import org.keycloak.representations.idm.ModuleRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.services.resources.flows.Flows;

/**
 * Base resource for managing module
 * 
 */
public class ModuleResource {

	protected ModuleModel module;
	protected ApplicationModel application;
	protected RealmModel realm;
    private RealmAuth auth;
    
    private final static Logger log = Logger.getLogger(ModuleResource.class);
	
	public ModuleResource(RealmModel realm, RealmAuth auth, ModuleModel module, ApplicationModel application) {
		this.realm = realm;
		this.auth = auth;
		this.module = module;
		this.application = application;
		
		auth.init(RealmAuth.Resource.MODULE);
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
            RepresentationToModel.updateModule(application, rep, module);
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
		application.removeModule(module);
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
		
        return ModelToRepresentation.toRepresentation(module);
    }
	
	/**
	 * Get this module
	 * @return
	 */
	@Path("roles")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public Set<RoleRepresentation> getModuleRoleMappings() {
//        auth.requireView();
		
		Set<RoleRepresentation> roles = new HashSet<RoleRepresentation>();
		Set<RoleModel> roleSet = module.getAllRoles();
		for (RoleModel model : roleSet) {
			roles.add(ModelToRepresentation.toRepresentation(model));
		}
		
        return roles;
    }
	
	@Path("roles")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    public Response addRoles(final @Context UriInfo uriInfo, String rolename) {
//        auth.requireView();
		
		RoleModel role = application.getRole(rolename);
		if (role != null) {
			if (!module.hasRole(role.getId())) {
				module.addRole(rolename);
				return Response.created(uriInfo.getAbsolutePathBuilder().path(ModulesResource.getModulePath(module)).build()).build();
			} else {
				return Flows.errors().exists("Role \"" + rolename + "\" already exists in module");
			}
		} else {
			return Flows.errors().exists("Role \"" + rolename + "\" is not found");
		}
    }
	
	@Path("roles")
	@DELETE
    public void deleteRoles(String rolename) {
//        auth.requireView();
		
		RoleModel role = application.getRole(rolename);
		if (role != null) {
			if (module.hasRole(role.getId())) {
				module.removeRole(rolename);
			}
		}
    }
	
	/**
	 * Get available module-level roles that can be mapped
	 * 
	 * @return
	 */
	@Path("available")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@NoCache
    public Set<RoleRepresentation> getAvailableModuleRoleMappings() {
//        auth.requireView();
		return getAvailableRoles(module, application.getRoles());
    }
    
    /**
     * Get effective module-level role mappings.  This recurses any composite roles
     *
     * @return
     */
    @Path("composite")
    @GET
    @Produces("application/json")
    @NoCache
    public List<RoleRepresentation> getCompositeModuleRoleMappings() {
        log.info("getCompositeModuleRoleMappings()");
    	//auth.requireView();

        Set<RoleModel> roles = module.getAllRoles();
        List<RoleRepresentation> mapRep = new ArrayList<RoleRepresentation>();
        for (RoleModel roleModel : roles) {
            mapRep.add(ModelToRepresentation.toRepresentation(roleModel));
        }
        return mapRep;
    }
    
    /**
     * 
     * @param module
     * @param available
     * @return
     */
    protected Set<RoleRepresentation> getAvailableRoles(ModuleModel module, Set<RoleModel> available) {
        Set<RoleModel> roles = new HashSet<RoleModel>();
        for (RoleModel roleModel : available) {
        	if (module.hasRole(roleModel.getId())) continue;
        	
            roles.add(roleModel);
        }

        Set<RoleRepresentation> mappings = new HashSet<RoleRepresentation>();
        for (RoleModel roleModel : roles) {
            mappings.add(ModelToRepresentation.toRepresentation(roleModel));
        }
        
        return mappings;
    }
	
}