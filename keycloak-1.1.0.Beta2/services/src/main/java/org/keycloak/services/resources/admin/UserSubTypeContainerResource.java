package org.keycloak.services.resources.admin;   

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.spi.NotFoundException;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserSubTypeContainerModel;
import org.keycloak.models.UserSubTypeModel;
import org.keycloak.models.UserTypeModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserSubTypeRepresentation;
import org.keycloak.services.resources.flows.Flows;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UserSubTypeContainerResource extends UserSubTypeResource {
    private final RealmModel realm;
    private final RealmAuth auth;
    protected UserSubTypeContainerModel userSubTypeContainer;
    
    private static final Logger logger = Logger.getLogger(UserSubTypeContainerResource.class);

    public UserSubTypeContainerResource(RealmModel realm, RealmAuth auth, UserSubTypeContainerModel userSubTypeContainer) {
        super(realm);
        this.realm = realm;
        this.auth = auth;
        this.userSubTypeContainer = userSubTypeContainer;
    }

    /**
     * List all userSubTypes for this realm or application
     *
     * @return
     */
    @GET
    @NoCache
    @Produces("application/json")
    public List<UserSubTypeRepresentation> getUserSubType() {
        auth.requireAny();
        System.out.println("############ getUserSubType all ");
        Set<UserSubTypeModel> userSubTypeModels = userSubTypeContainer.getUserSubTypes();
        List<UserSubTypeRepresentation> userSubTypes = new ArrayList<UserSubTypeRepresentation>();
        for (UserSubTypeModel userSubTypeModel : userSubTypeModels) {
        	userSubTypes.add(ModelToRepresentation.toRepresentation(userSubTypeModel));
        }
        return userSubTypes;
    }

    /**
     * Create a new userSubType for this realm or application
     *
     * @param uriInfo
     * @param rep
     * @return
     */
    @POST
    @Consumes("application/json")
    public Response createUserSubType(final @Context UriInfo uriInfo, final UserSubTypeRepresentation rep) {
        auth.requireManage();
        System.out.println("############ createUserSubType :" + rep.getName() + " " + rep.getUserType());
        System.out.println("############ : " + uriInfo.getPath());
        System.out.println("############ : " + uriInfo.getAbsolutePath());
        try {
            UserSubTypeModel userSubType = userSubTypeContainer.addUserSubType(rep.getName());
            UserTypeModel userType = realm.getUserTypeById(rep.getUserType());
            if (userType == null) {
                throw new NotFoundException("Could not find User Type: " + rep.getName());
            }
            userSubType.setUserType(userType);
            return Response.created(uriInfo.getAbsolutePathBuilder().path(userSubType.getName()).build()).build();
        } catch (ModelDuplicateException e) {
            return Flows.errors().exists("UserSubType with name " + rep.getName() + " already exists");
        }
    }

    /**
     * Get a userSubType by name
     *
     * @param userSubTypeName userSubType's name (not id!)
     * @return
     */
    @Path("{userSubType}")
    @GET
    @NoCache
    @Produces("application/json")
    public UserSubTypeRepresentation getUserSubType(final @PathParam("userSubType") String userSubTypeName) {
        auth.requireView();
        
        System.out.println("############ getUserSubType " + userSubTypeName);
        
        UserSubTypeModel userSubTypeModel = userSubTypeContainer.getUserSubType(userSubTypeName);
        if (userSubTypeModel == null || userSubTypeModel.getId() == null) {
        	System.out.println("Could not find user subtype" + userSubTypeName);
        	 throw new NotFoundException("Could not find user sub type: "+userSubTypeName);
        }
        return getUserSubType(userSubTypeModel);
    }

    /**
     * Delete a userSubType by name
     *
     * @param userSubTypeId userSubType's name (not id!)
     */
    @Path("{userSubType-id}")
    @DELETE
    @NoCache
    public void deleteUserSubType(final @PathParam("userSubType-id") String userSubTypeId) {
        auth.requireManage();

        System.out.println("############ deleteUserSubType " + userSubTypeId);
        UserSubTypeModel userSubType = userSubTypeContainer.getUserSubTypeById(userSubTypeId);
        if (userSubType == null) {
            throw new NotFoundException("Could not find userSubTypeId: " + userSubTypeId);
        }
        deleteUserSubType(userSubType);
        System.out.println("############ DONE deleteUserSubType " + userSubTypeId);
    }

    /**
     * Update a userSubType by name
     *
     * @param userSubTypeId userSubType's name (not id!)
     * @param rep
     * @return
     */
    @Path("{userSubType-id}")
    @PUT
    @Consumes("application/json")
    public Response updateUserSubType(final @PathParam("userSubType-id") String userSubTypeId, final UserSubTypeRepresentation rep) {
        auth.requireManage();
        System.out.println("############ DONE updateUserSubType " + userSubTypeId);
        UserSubTypeModel userSubType = userSubTypeContainer.getUserSubTypeById(userSubTypeId);        
        
        if (userSubType == null) {
            throw new NotFoundException("Could not find userSubTypeName: " + userSubTypeId);
        }
        
        try {
            updateUserSubType(rep, userSubType);
            System.out.println("############ DONE updateUserSubType " + userSubTypeId);
            return Response.noContent().build();
        } catch (ModelDuplicateException e) {
            return Flows.errors().exists("UserSubType with name " + rep.getName() + " already exists");
        }
        
    }
}
