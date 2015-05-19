package org.keycloak.example.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.AdapterUtils;
import org.keycloak.adapters.HttpClientBuilder;
import org.keycloak.representations.idm.ModuleRepresentation;
import org.keycloak.util.JsonSerialization;

public class ModuleClient {

	private final static Log log = LogFactory.getLog(ModuleClient.class); 
	
	static class TypedList extends ArrayList<ModuleRepresentation> {
		
    }

    public static class Failure extends Exception {
        private int status;

        public Failure(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }
    
    public static List<ModuleRepresentation> getModules(HttpServletRequest req) throws Failure {
    	KeycloakSecurityContext session = (KeycloakSecurityContext) req.getAttribute(KeycloakSecurityContext.class.getName());
    	HttpClient client = new HttpClientBuilder().disableTrustManager().build();
		try {
			// admin/realms/{realm}/applications/{app-name}
		    HttpGet get = new HttpGet(AdapterUtils.getOrigin(req.getRequestURL().toString(), session) + "/auth/admin/realms/"+session.getRealm()+"/applications/product-portal/modules");
		    get.addHeader("Authorization", "Bearer " + session.getTokenString());
		    try {
		        HttpResponse response = client.execute(get);
		        if (response.getStatusLine().getStatusCode() != 200) {
		            throw new Failure(response.getStatusLine().getStatusCode());
		        }
		        HttpEntity entity = response.getEntity();
		        InputStream is = entity.getContent();
		        
		        //Create BufferedReader object
                BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
                StringBuffer sbfFileContents = new StringBuffer();
                String line = null;
               
                //read file line by line
                while( (line = bReader.readLine()) != null){
                    sbfFileContents.append(line);
                }
               
                //finally convert StringBuffer object to String!
                log.info(sbfFileContents.toString());
		        
		        try {
		            return JsonSerialization.readValue(sbfFileContents.toString(), TypedList.class);
		        } finally {
		            is.close();
		        }
		    } catch (IOException e) {
		        throw new RuntimeException(e);
		    }
		} finally {
		    client.getConnectionManager().shutdown();
		}
    }
    
}
