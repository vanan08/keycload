package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.security.Principal;
import java.util.*;
import org.picketlink.identity.federation.web.constants.GeneralConstants;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      response.addHeader("X-Powered-By", "JSP/2.2");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write('\n');


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

      out.write("\n");
      out.write("<div align=\"center\">\n");
      out.write("<h1>SalesDashboard</h1>\n");
      out.write("<br/>\n");
      out.write("Welcome to the Employee Tool, <b>");
      out.print(userPrincipal.getName());
      out.write("</b>.  \n");
      out.write("<br/>\n");
      out.write("<img src=\"careermap.jpg\"/>\n");
      out.write("<br/>\n");
      out.write("<a href=\"?GLO=true\">Click to LogOut</a>\n");
      out.write("\n");
      out.write("</div>\n");
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
