package org.apache.jsp.products;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.keycloak.util.UriUtils;
import org.keycloak.representations.idm.ModuleRepresentation;
import org.keycloak.example.oauth.ModuleClient;
import org.keycloak.constants.ServiceUrlConstants;
import org.keycloak.util.KeycloakUriBuilder;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      response.addHeader("X-Powered-By", "JSP/2.2");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Product Portal</title>\n");
      out.write("</head>\n");
      out.write("<body bgcolor=\"#F5F6CE\">\n");

    String logoutUri = KeycloakUriBuilder.fromUri("/auth").path(ServiceUrlConstants.TOKEN_SERVICE_LOGOUT_PATH)
            .queryParam("redirect_uri", "https://localhost:8443/product-portal").build("demo").toString();
    String acctUri = KeycloakUriBuilder.fromUri("/auth").path(ServiceUrlConstants.ACCOUNT_SERVICE_PATH)
            .queryParam("referrer", "https://localhost:8443/product-portal").build("demo").toString();

      out.write("\n");
      out.write("\n");
      out.write("<p>Goto: <a href=\"");
      out.print(logoutUri);
      out.write("\">logout</a> | <a href=\"");
      out.print(acctUri);
      out.write("\">manage acct</a></p>\n");
      out.write("User <b>");
      out.print(request.getUserPrincipal().getName());
      out.write("</b> made this request.\n");
      out.write("<h2>Module Listing</h2>\n");


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


      out.write("\n");
      out.write("<br><br>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
