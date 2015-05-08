package org.keycloak.services.resources.admin;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.spi.NotFoundException;
import org.keycloak.models.ApplicationModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.models.utils.RepresentationToModel;
import org.keycloak.representations.idm.ModuleRepresentation;
import org.keycloak.services.resources.flows.Flows;

/**
 * Base resource for managing modules
 * 
 */
public class ModulesResource {
	
	protected static final Logger logger = Logger.getLogger(ModulesResource.class);
	protected ApplicationModel applicationModel;
    protected RealmModel realm;
    private RealmAuth auth;

    @Context
    protected KeycloakSession session;
    
    public ModulesResource(RealmModel realm, RealmAuth auth, ApplicationModel applicationModel) {
        this.realm = realm;
        this.auth = auth;
        this.applicationModel = applicationModel;

        auth.init(RealmAuth.Resource.APPLICATION);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public List<ModuleRepresentation> getModules() {
//        auth.requireAny();
        
//        ApplicationModel application = realm.getApplicationById(appId);
        List<ModuleRepresentation> rep = new ArrayList<ModuleRepresentation>();

//        boolean view = auth.hasView();
        for (ModuleModel moduleModel : applicationModel.getModules()) {
//            if (view) {
                rep.add(ModelToRepresentation.toRepresentation(moduleModel));
//            } else {
//                ModuleRepresentation module = new ModuleRepresentation();
//                module.setName(moduleModel.getName());
//                rep.add(module);
//            }
        }
        return rep;
    }
    
    /**
     * Create a new module
     * @param uriInfo
     * @param rep
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModule(final @Context UriInfo uriInfo, final ModuleRepresentation rep) {
//    	auth.requireManage();
    	
    	try {
//    		ApplicationModel application = realm.getApplicationById(appId);
    		ModuleModel moduleModel = RepresentationToModel.createModule(realm, applicationModel, rep);
    		return Response.created(uriInfo.getAbsolutePathBuilder().path(getModulePath(moduleModel)).build()).build();
    	} catch (ModelDuplicateException e) {
            return Flows.errors().exists("Module " + rep.getName() + " already exists");
        }
    }
    
    protected String getModulePath(ModuleModel moduleModel) {
        return moduleModel.getName();
    }
    
    /**
     * Base path for managing a specific module
     * @param modId Id's module
     * @return
     */
    @Path("{mod-name}")
    public ModuleResource getModule(final @PathParam("mod-name") String modName) {
    	//logger.info("module - number of modules: "+applicationModel.getModules().size());
    	ModuleModel module = applicationModel.getModuleByName(modName);
        if (module == null) {
            throw new NotFoundException("Could not find module: " + modName);
        }
        
        ModuleResource moduleResource = new ModuleResource(realm, auth, module, applicationModel);
        //ResteasyProviderFactory.getInstance().injectProperties(moduleResource);
        //resourceContext.initResource(moduleResource);
        return moduleResource;
    }
    
    protected ApplicationModel getApplicationByPathParam(String name) {
		return realm.getApplicationByName(name);
	}
	
}
