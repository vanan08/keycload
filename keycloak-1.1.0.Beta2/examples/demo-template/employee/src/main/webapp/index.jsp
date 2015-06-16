<%-- <%@ page import="java.security.Principal" %>
<%@ page import="java.util.*" %>
<%@ page import="org.picketlink.identity.federation.web.constants.GeneralConstants" %> --%>
<%

/* Principal userPrincipal = (Principal) session.getAttribute(GeneralConstants.PRINCIPAL_ID);

System.out.println("printing http headers (start) -----------------------------------"); 
Enumeration eNames = request.getHeaderNames();
while (eNames.hasMoreElements()) {
    String name = (String) eNames.nextElement();
    String value = (String)(request.getHeader(name));
    
    System.out.println(name+"="+value);
}
System.out.println("printing http headers (end) -----------------------------------"); 
        

if(userPrincipal!=null) { */
    out.println("<a href=\"https://localhost:8443/employee/run1.jsp\">Module 1</a>");
    out.println("<BR>");
    out.println("<BR>");
 	out.println("<a href=\"https://localhost:8443/employee/run2.jsp\">Module 2</a>");
    out.println("<BR>");
    out.println("<BR>");
/* } */
%>