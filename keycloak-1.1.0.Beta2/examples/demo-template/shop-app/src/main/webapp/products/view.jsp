
<%@page import="org.shop.app.ModuleClient"%>
<%@page import="org.keycloak.util.UriUtils"%>
<%@page import="org.keycloak.representations.idm.ModuleRepresentation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"%>
<%@ page import="org.keycloak.constants.ServiceUrlConstants" %>
<%@ page import="org.keycloak.util.KeycloakUriBuilder" %>
<%@ page session="false" %>
<html>
<head>
    <title>Product page</title>
</head>
<body bgcolor="#F5F6CE">
<%
    String logoutUri = KeycloakUriBuilder.fromUri("/auth").path(ServiceUrlConstants.TOKEN_SERVICE_LOGOUT_PATH)
            .queryParam("redirect_uri", "https://localhost:8443/shop-app").build("demo").toString();
    String acctUri = KeycloakUriBuilder.fromUri("/auth").path(ServiceUrlConstants.ACCOUNT_SERVICE_PATH)
            .queryParam("referrer", "https://localhost:8443/shop-app").build("demo").toString();
%>

<h2>This is page product</h2>

<br><br>
</body>
</html>