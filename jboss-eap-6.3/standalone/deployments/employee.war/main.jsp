<%@ page import="java.security.Principal" %>
<%@ page import="java.util.*" %>
<%@ page import="org.picketlink.identity.federation.web.constants.GeneralConstants" %>
<%
Principal userPrincipal = (Principal) session.getAttribute(GeneralConstants.PRINCIPAL_ID);
String myRole="";
List roles = (List) request.getSession().getAttribute("picketlink.roles");
        	if(roles!=null) {
        		for (int i=0;i<roles.size();i++) {
        			myRole = (String) roles.get(i);
        			System.out.println("user role="+myRole);
        		}
        	}

if(userPrincipal!=null)
   System.out.println("userPrincipal is not null");
else
 System.out.println("userPrincipal is   null");
%>
<div align="center">
<h1>EmployeeDashboard</h1>
<br/>
Welcome to the Employee Tool, <b><%=userPrincipal.getName()%></b>.  
<br/>
Role, <b><%=myRole%></b>.  
<br/>

<img src="careermap.jpg"/>
<br/>
<a href="?GLO=true">Click to LogOut</a>

</div>
