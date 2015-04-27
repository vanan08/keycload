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
import org.jboss.resteasy.spi.ResteasyProviderFactory;
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
    protected RealmModel realm;
    private RealmAuth auth;

    @Context
    protected KeycloakSession session;
    
    public ModulesResource(RealmModel realm, RealmAuth auth) {
        this.realm = realm;
        this.auth = auth;

        auth.init(RealmAuth.Resource.MODULE);
    }
    
    @Path("{app-name}/application/modules")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public List<ModuleRepresentation> getModules(final @PathParam("app-name") String name) {
        auth.requireAny();
        
        ApplicationModel application = realm.getApplicationByName(name);
        List<ModuleRepresentation> rep = new ArrayList<ModuleRepresentation>();
        List<ModuleModel> moduleModels = new ArrayList<ModuleModel>(application.getModules());

        boolean view = auth.hasView();
        for (ModuleModel moduleModel : moduleModels) {
            if (view) {
                rep.add(ModelToRepresentation.toRepresentation(moduleModel));
            } else {
                ModuleRepresentation module = new ModuleRepresentation();
                module.setName(moduleModel.getName());
                rep.add(module);
            }
        }
        return rep;
    }
    
    /**
     * Create a new module
     * @param uriInfo
     * @param appId
     * @param rep
     * @return
     */
    @Path("applications/{app-id}/module")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModule(final @Context UriInfo uriInfo, final @PathParam("app-id") String appId, final ModuleRepresentation rep) {
    	auth.requireManage();
    	
    	try {
    		ApplicationModel application = realm.getApplicationById(appId);
    		ModuleModel moduleModel = RepresentationToModel.createModule(realm, application, rep);
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
     * @param appId Id's application
     * @param modId Id's module
     * @return
     */
    @Path("applications/{app-id}/modules/{mod-id}")
    public ModuleResource getModule(final @PathParam("app-id") String appId, final @PathParam("mod-id") String modId) {
        ApplicationModel application = getApplicationByPathParam(appId);
        if (application == null) {
        	throw new NotFoundException("Could not find application: " + appId);
        }
        
    	ModuleModel module = application.getModuleById(modId);
        if (module == null) {
            throw new NotFoundException("Could not find module: " + modId);
        }
        
        ModuleResource moduleResource = new ModuleResource(realm, auth, module);
        ResteasyProviderFactory.getInstance().injectProperties(moduleResource);
        //resourceContext.initResource(moduleResource);
        return moduleResource;
    }
    
    protected ApplicationModel getApplicationByPathParam(String name) {
		return realm.getApplicationByName(name);
	}
	
}
