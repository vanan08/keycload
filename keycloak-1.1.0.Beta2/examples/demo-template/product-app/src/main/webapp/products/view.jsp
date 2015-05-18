
<%@page import="org.keycloak.util.UriUtils"%>
<%@page import="org.keycloak.representations.idm.ModuleRepresentation"%>
<%@ page import="org.keycloak.example.oauth.ModuleClient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"%>
<%@ page import="org.keycloak.constants.ServiceUrlConstants" %>
<%@ page import="org.keycloak.util.KeycloakUriBuilder" %>
<%@ page session="false" %>
<html>
<head>
    <title>Product Portal</title>
</head>
<body bgcolor="#F5F6CE">
<%
    String logoutUri = KeycloakUriBuilder.fromUri("/auth").path(ServiceUrlConstants.TOKEN_SERVICE_LOGOUT_PATH)
            .queryParam("redirect_uri", "/product-portal").build("demo").toString();
    String acctUri = KeycloakUriBuilder.fromUri("/auth").path(ServiceUrlConstants.ACCOUNT_SERVICE_PATH)
            .queryParam("referrer", "/product-portal").build("demo").toString();
%>

<p>Goto: <a href="<%=logoutUri%>">logout</a> | <a href="<%=acctUri%>">manage acct</a></p>
User <b><%=request.getUserPrincipal().getName()%></b> made this request.
<h2>Module Listing</h2>
<%

	java.util.List<ModuleRepresentation> list = null;
	try {
		list = ModuleClient.getModules(request);
	} catch (ModuleClient.Failure failure) {
        out.println("There was a failure processing request.  You either didn't configure Keycloak properly");
        out.println("Status from Keycloak invocation was: " + failure.getStatus());
        return;
    }
	
	out.print(UriUtils.getOrigin(request.getRequestURL().toString()));
	
	for (ModuleRepresentation modRep : list)
	{
	   out.print("<p>");
	   out.print(modRep.getName());
	   out.println("</p>");
	}

%>
<br><br>
</body>
</html>