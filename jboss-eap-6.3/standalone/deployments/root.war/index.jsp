<%@ page import="java.security.Principal" %>
<%@ page import="java.util.*" %>
<%@ page import="org.picketlink.identity.federation.web.constants.GeneralConstants" %>
<%

Principal userPrincipal = (Principal) session.getAttribute(GeneralConstants.PRINCIPAL_ID);

      System.out.println("printing http headers (start) -----------------------------------"); 
        Enumeration eNames = request.getHeaderNames();
        while (eNames.hasMoreElements()) {
           String name = (String) eNames.nextElement();
           String value = (String)(request.getHeader(name));
           
           System.out.println(name+"="+value);
        }
        System.out.println("printing http headers (end) -----------------------------------"); 
        

if(userPrincipal!=null) {
            out.println("<a href=\"http://192.168.222.76/pruone\" target=\"_blank\">PRUONE APPLICATION</a>");
  	    out.println("<BR>");
  	    out.println("<BR>");
  	    out.println("<a href=\"https://sfa-uact.prudential.com.sg/prusales/ssoaccess.do\" target=\"_blank\">SFA Application</a>");
  	    out.println("<BR>");
  	    out.println("<BR>");
  	    out.println("<a href=\"https://localhost:8443/sales-post/main.jsp\" target=\"_blank\">Sales Application</a>");
  	    out.println("<BR>");
  	    out.println("<BR>");
  	    out.println("<a href=\"https://localhost:8443/employee/main.jsp\" target=\"_blank\">Employee Application</a>");
  	    out.println("<BR>");
  	    out.println("<BR>");
}
%>