package org.keycloak.services.resources.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.spi.NotFoundException;
import org.keycloak.models.ApplicationModel;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

public class UserModuleRoleMappingsResource {
	
	protected RealmModel realm;
    protected RealmAuth auth;
    protected UserModel user;
    protected ApplicationModel application;
    
    protected static final Logger logger = Logger.getLogger(UserModuleRoleMappingsResource.class);

    public UserModuleRoleMappingsResource(RealmModel realm, RealmAuth auth, UserModel user, ApplicationModel application) {
        this.realm = realm;
        this.auth = auth;
        this.user = user;
        this.application = application;
    }
    
    private ModuleModel getModule(String modId) {
    	return application.getModuleById(modId);
    }
    
    /**
     * Get available module-level roles that can be mapped to the user
     *
     * @return
     */
    @Path("{modId}/available")
    @GET
    @Produces("application/json")
    @NoCache
    public List<RoleRepresentation> getAvailableApplicationRoleMappings(final @PathParam("modId") String modId) {
        //auth.requireView();

    	logger.debug("getAvailableApplicationRoleMappings");
    	
    	ModuleModel module = getModule(modId);

        if (module == null) {
            throw new NotFoundException("Module not found");
        }
    	
        Set<RoleModel> available = user.getApplicationRoleMappings(application);
        return getAvailableRoles(user, module, available);
    }
    
    public static List<RoleRepresentation> getAvailableRoles(UserModel user, ModuleModel module, Set<RoleModel> available) {
        Set<RoleModel> roles = new HashSet<RoleModel>();
        for (RoleModel roleModel : available) {
            if (module.container(user.getId(), roleModel)) {
            	continue;
            }
            roles.add(roleModel);
        }

        List<RoleRepresentation> mappings = new ArrayList<RoleRepresentation>();
        for (RoleModel roleModel : roles) {
            mappings.add(ModelToRepresentation.toRepresentation(roleModel));
        }
        
        logger.debugv("getAvailableApplicationRoleMappings.size() = {0}", mappings.size());
        
        return mappings;
    }
    
    /**
     * Get module-level role mappings for this user for a specific module
     *
     * @return
     */
    @Path("{modId}")
    @GET
    @Produces("application/json")
    @NoCache
    public List<RoleRepresentation> getModuleRoleMappings(final @PathParam("modId") String modId) {
        //auth.requireView();

        logger.debug("getModuleRoleMappings");

        ModuleModel module = getModule(modId);

        if (module == null) {
            throw new NotFoundException("Module not found");
        }
        
        Set<RoleModel> rolesModule = module.getRoles(user.getId());
        logger.info("userId="+user.getId()+",module="+module.getName()+",rolesModule="+rolesModule.size());
        
        Set<RoleModel> mappings = user.getModuleRoleMappings(module);
        logger.info("userId="+user.getId()+",module="+module.getName()+",rolesMapping="+mappings.size());
        
        List<RoleRepresentation> mapRep = new ArrayList<RoleRepresentation>();
        for (RoleModel roleModel : mappings) {
            mapRep.add(ModelToRepresentation.toRepresentation(roleModel));
        }
        logger.info("getModuleRoleMappings.size() = "+ mapRep.size());
        
        return mapRep;
    }
    
    /**
     * Get effective module-level role mappings.  This recurses any composite roles
     *
     * @return
     */
    @Path("{modId}/composite")
    @GET
    @Produces("application/json")
    @NoCache
    public List<RoleRepresentation> getCompositeModuleRoleMappings(final @PathParam("modId") String modId) {
        //auth.requireView();

        logger.debug("getCompositeModuleRoleMappings");

        ModuleModel module = getModule(modId);

        if (module == null) {
            throw new NotFoundException("Module not found");
        }        
        
        Set<RoleModel> roles = module.getRoles(user.getId());
        List<RoleRepresentation> mapRep = new ArrayList<RoleRepresentation>();
        for (RoleModel roleModel : roles) {
            if (user.hasRole(roleModel)) mapRep.add(ModelToRepresentation.toRepresentation(roleModel));
        }
        logger.debugv("getCompositeModuleRoleMappings.size() = {0}", mapRep.size());
        return mapRep;
    }
    
    /**
     * Add module-level roles to the user role mapping.
     *
      * @param roles
     */
    @Path("{modId}")
    @POST
    @Consumes("application/json")
    public void addModuleRoleMapping(final @PathParam("modId") String modId, List<RoleRepresentation> roles) {
        //auth.requireManage();

        logger.debug("addModuleRoleMapping");
        
        ModuleModel module = getModule(modId);

        if (module == null) {
            throw new NotFoundException("Module not found");
        }
        
        for (RoleRepresentation role : roles) {
            RoleModel roleModel = application.getRole(role.getName());
            if (roleModel == null || !roleModel.getId().equals(role.getId())) {
                throw new NotFoundException("Role not found");
            }
            module.addRole(user.getId(), roleModel.getName());
        }
    }

    /**
     * Delete module-level roles from user role mapping.
     *
     * @param roles
     */
    @Path("{modId}")
    @DELETE
    @Consumes("application/json")
    public void deleteModuleRoleMapping(final @PathParam("modId") String modId, List<RoleRepresentation> roles) {
        //auth.requireManage();
    	
    	logger.info("deleteModuleRoleMapping");

    	ModuleModel module = getModule(modId);

        if (module == null) {
            throw new NotFoundException("Module not found");
        }
    	
        if (roles == null || roles.size() == 0) {
            Set<RoleModel> roleModels = user.getModuleRoleMappings(module);
            logger.info("number of roles : " + roleModels.size());
            for (RoleModel roleModel : roleModels) {
                module.removeRole(user.getId(), roleModel);
            }
        } else {
            for (RoleRepresentation role : roles) {
                RoleModel roleModel = module.getRoleByName(user.getId(), role.getName());
                if (roleModel == null || !roleModel.getId().equals(role.getId())) {
                    throw new NotFoundException("Role not found");
                }
                module.removeRole(user.getId(), roleModel);
            }
        }
    }

}
