package com.prudential;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.keycloak.adapters.HttpClientBuilder;
import org.keycloak.representations.idm.ModuleRepresentation;
import org.keycloak.util.JsonSerialization;

public class ModuleService {
	
	//private final static Log log = LogFactory.getLog(ModuleService.class); 
	
	/*public static class Failure extends Exception {
        private int status;

        public Failure(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }*/
	
	public static ModuleRepresentation getModuleByApp(String moduleName) {
		HttpClient client = new HttpClientBuilder().disableTrustManager().build();
		
		try {
			HttpGet get = new HttpGet("https://localhost:8443/auth/modules/"+moduleName+"/info");
			HttpResponse response = client.execute(get);
			
			if (response.getStatusLine().getStatusCode() != 200) {
	            //throw new Failure(response.getStatusLine().getStatusCode());
				return null;
	        }
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        
	        //Create BufferedReader object
	        BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
	        StringBuffer sbfFileContents = new StringBuffer();
	        String line = null;
	       
	        //read file line by line
	        while( (line = bReader.readLine()) != null){
	            sbfFileContents.append(line).append(" ");
	        }
	       
	        //finally convert StringBuffer object to String!
	        //log.info(sbfFileContents.toString());
			
			try {
	            return JsonSerialization.readValue(sbfFileContents.toString(), ModuleRepresentation.class);
	        } finally {
	            is.close();
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    client.getConnectionManager().shutdown();
		}
		
		return null;
	}
	
}
