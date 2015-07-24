package org.keycloak.services.resources.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.spi.NotFoundException;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserTypeContainerModel;
import org.keycloak.models.UserTypeModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserTypeRepresentation;
import org.keycloak.services.resources.flows.Flows;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UserTypeContainerResource extends UserTypeResource {
    private final RealmModel realm;
    private final RealmAuth auth;
    protected UserTypeContainerModel userTypeContainer;
    
    private static final Logger logger = Logger.getLogger(UserTypeContainerResource.class);

    public UserTypeContainerResource(RealmModel realm, RealmAuth auth, UserTypeContainerModel userTypeContainer) {
        super(realm);
        this.realm = realm;
        this.auth = auth;
        this.userTypeContainer = userTypeContainer;
    }

    /**
     * List all userTypes for this realm or application
     *
     * @return
     */
    @GET
    @NoCache
    @Produces("application/json")
    public List<UserTypeRepresentation> getUserType(@QueryParam("search") String search,
													@QueryParam("first") Integer firstResult,
													@QueryParam("max") Integer maxResults) {
        //auth.requireAny();
        
        List<UserTypeRepresentation> userTypes = new ArrayList<UserTypeRepresentation>();
        
        logger.info("############ getUserType");
        logger.info("############ search key:" + search);
        logger.info("############ first" + firstResult);
        logger.info("############ max" + maxResults);
        //Set<UserTypeModel> userTypeModels = userTypeContainer.getUserTypes();
        if(firstResult == null) firstResult = -1;
        if(maxResults == null) maxResults = -1;
        List<UserTypeModel> userTypeModels = userTypeContainer.getUserTypes(realm, search, firstResult, maxResults);
        
        for (UserTypeModel userTypeModel : userTypeModels) {
        	userTypes.add(ModelToRepresentation.toRepresentation(userTypeModel));
        }
        
        return userTypes;
    }

    /**
     * Create a new userType for this realm or application
     *
     * @param uriInfo
     * @param rep
     * @return
     */
    @POST
    @Consumes("application/json")
    public Response createUserType(final @Context UriInfo uriInfo, final UserTypeRepresentation rep) {
        auth.requireManage();
        logger.info("############ createUserType :" + rep.getName() + " " + rep.getTncContent() 
        		+ " " +rep.getUserTypeRole()+ " " +rep.getRedirectUrl());
        logger.info("############ : " + uriInfo.getPath());
        logger.info("############ : " + uriInfo.getAbsolutePath());
        try {
            UserTypeModel userType = userTypeContainer.addUserType(rep.getName());
            userType.setTncContent(rep.getTncContent());
            userType.setUserTypeRole(rep.getUserTypeRole());
            return Response.created(uriInfo.getAbsolutePathBuilder().path(userType.getName()).build()).build();
        } catch (ModelDuplicateException e) {
            return Flows.errors().exists("UserType with name " + rep.getName() + " already exists");
        }
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserType(final @Context UriInfo uriInfo, MultipartFormDataInput input) {
        auth.requireManage();
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        //UserTypeRepresentation rep = new UserTypeRepresentation();
        String name = "";
        String id = "";
        String userTypeRole = "";
        String redirectUrl = "";
        try {
        	name = uploadForm.get("userTypeName").get(0).getBodyAsString();
        	id = uploadForm.get("userTypeId").get(0).getBodyAsString();
        	userTypeRole = uploadForm.get("roleNames").get(0).getBodyAsString();
        	redirectUrl = uploadForm.get("redirectUrl").get(0).getBodyAsString();
	        logger.info("*********** createUserType NEW :" + name);	     

	        if(id.equals(" ")){
	            UserTypeModel userType = userTypeContainer.addUserType(name);
	            InputStream inputStream = inputParts.get(0).getBody(InputStream.class, null);
	            userType.setTncContent(IOUtils.toByteArray(inputStream));
            	userType.setUserTypeRole(userTypeRole);
            	userType.setRedirectUrl(redirectUrl);
	            
	        }else{
	            UserTypeModel userType = userTypeContainer.getUserTypeById(id);
	            userType.setName(name);
	            userType.setRedirectUrl(redirectUrl);
            	InputStream inputStream = inputParts.get(0).getBody(InputStream.class, null);
            	userType.setTncContent(IOUtils.toByteArray(inputStream));
            	userType.setUserTypeRole(userTypeRole);
	        }
	        
            return Response.created(uriInfo.getAbsolutePathBuilder().path(name).build()).build();
        } catch (ModelDuplicateException e) {
        	return Flows.errors().exists("UserType with name " + name + " already exists");
        } catch (IOException e1) {
        	e1.printStackTrace();
        	return Flows.errors().exists("Can not creat user type");
        }
    }

    /**
     * Get a userType by name
     *
     * @param userTypeName userType's name (not id!)
     * @return
     */
    @Path("{userType-name}")
    @GET
    @NoCache
    @Produces("application/json")
    public UserTypeRepresentation getUserType(final @PathParam("userType-name") String userTypeName) {
        auth.requireView();        
        logger.info("############ getUserType " + userTypeName);        
        UserTypeModel userTypeModel = userTypeContainer.getUserType(userTypeName);
        
        if (userTypeModel == null || userTypeModel.getId() == null) {
            throw new NotFoundException("Could not find userTypeName: " + userTypeName);
        }
        
        return getUserType(userTypeModel);
    }

    /**
     * Delete a userType by name
     *
     * @param userTypeId userType's name (not id!)
     */
    @Path("{userType-id}")
    @DELETE
    @NoCache
    public void deleteUserType(final @PathParam("userType-id") String userTypeId) {
        auth.requireManage();
        logger.info("############ deleteUserType " + userTypeId);
        UserTypeModel userType = userTypeContainer.getUserTypeById(userTypeId);
        
        if (userType == null) {
            throw new NotFoundException("Could not find userTypeId: " + userTypeId);
        }
        
        deleteUserType(userType);
        logger.info("############ DONE deleteUserType " + userTypeId);
    }

    /**
     * Update a userType by name
     *
     * @param userTypeId userType's name (not id!)
     * @param rep
     * @return
     */
    @Path("{userType-id}")
    @PUT
    @Consumes("application/json")
    public Response updateUserType(final @PathParam("userType-id") String userTypeId, final UserTypeRepresentation rep) {
        auth.requireManage();
        logger.info("############ DONE updateUserType " + userTypeId);
        UserTypeModel userType = userTypeContainer.getUserTypeById(userTypeId);
        
        if (userType == null) {
            throw new NotFoundException("Could not find userTypeName: " + userTypeId);
        }
        
        try {
        	userType.setName(rep.getName());
        	userType.setUserTypeRole(rep.getUserTypeRole());
            logger.info("####******* DONE updateUserTypeName " + userTypeId);
            return Response.noContent().build();
        } catch (Exception e) {
        	 return Flows.errors().exists("UserType " + rep.getName() + " could not updated");
        }
        
    }
}
