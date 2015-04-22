package org.keycloak.admin.client.token;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.admin.client.Config;
import org.keycloak.admin.client.resource.BasicAuthFilter;
import org.keycloak.representations.AccessTokenResponse;

import javax.ws.rs.core.Form;
import java.util.Calendar;
import java.util.Date;

/**
 * @author rodrigo.sasaki@icarros.com.br
 */
public class TokenManager {

    private AccessTokenResponse currentToken;
    private Date expirationTime;
    private Config config;

    public TokenManager(Config config){
        this.config = config;
    }

    public String getAccessTokenString(){
        return getAccessToken().getToken();
    }

    public AccessTokenResponse getAccessToken(){
        if(currentToken == null){
            grantToken();
        }else if(tokenExpired()){
            refreshToken();
        }
        return currentToken;
    }

    public AccessTokenResponse grantToken(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(config.getServerUrl());

        Form form = new Form()
                .param("username", config.getUsername())
                .param("password", config.getPassword());

        if(config.isPublicClient()){
            form.param("client_id", config.getClientId());
        } else {
            target.register(new BasicAuthFilter(config.getClientId(), config.getClientSecret()));
        }

        TokenService tokenService = target.proxy(TokenService.class);

        AccessTokenResponse response = tokenService.grantToken(config.getRealm(), form.asMap());

        defineCurrentToken(response);
        return response;
    }

    public AccessTokenResponse refreshToken(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(config.getServerUrl());

        Form form = new Form()
                .param("username", config.getUsername())
                .param("password", config.getPassword());

        if(config.isPublicClient()){
            form.param("client_id", config.getClientId());
        } else {
            target.register(new BasicAuthFilter(config.getClientId(), config.getClientSecret()));
        }

        TokenService tokenService = target.proxy(TokenService.class);

        AccessTokenResponse response = tokenService.refreshToken(config.getRealm(), form.asMap());

        defineCurrentToken(response);
        return response;
    }

    private void setExpirationTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, (int) currentToken.getExpiresIn());
        expirationTime = cal.getTime();
    }

    private boolean tokenExpired() {
        return new Date().after(expirationTime);
    }

    private void defineCurrentToken(AccessTokenResponse accessTokenResponse){
        currentToken = accessTokenResponse;
        setExpirationTime();
    }

}
