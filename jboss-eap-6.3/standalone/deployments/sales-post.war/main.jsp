<%@ page import="java.security.Principal" %>
<%@ page import="java.util.*" %>
<%@ page import="org.picketlink.identity.federation.web.constants.GeneralConstants" %>
<%

Principal userPrincipal = (Principal) session.getAttribute(GeneralConstants.PRINCIPAL_ID);

List roles = (List) request.getSession().getAttribute("picketlink.roles");
        	if(roles!=null) {
        		for (int i=0;i<roles.size();i++) {
        			String role = (String) roles.get(i);
        			System.out.println("user role="+role);
        		}
        	}

if(userPrincipal!=null)
   System.out.println("userPrincipal is not null");
else
 System.out.println("userPrincipal is   null");
%>
<div align="center">
<h1>SalesDashboard</h1>
<br/>
Welcome to the Employee Tool, <b><%=userPrincipal.getName()%></b>.  
<br/>
<img src="careermap.jpg"/>
<br/>
<a href="?GLO=true">Click to LogOut</a>

</div>
