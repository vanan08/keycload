package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.Cookie;

public final class logout1_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!--\n");
      out.write("  ~ JBoss, Home of Professional Open Source.\n");
      out.write("  ~ Copyright (c) 2011, Red Hat, Inc., and individual contributors\n");
      out.write("  ~ as indicated by the @author tags. See the copyright.txt file in the\n");
      out.write("  ~ distribution for a full listing of individual contributors.\n");
      out.write("  ~\n");
      out.write("  ~ This is free software; you can redistribute it and/or modify it\n");
      out.write("  ~ under the terms of the GNU Lesser General Public License as\n");
      out.write("  ~ published by the Free Software Foundation; either version 2.1 of\n");
      out.write("  ~ the License, or (at your option) any later version.\n");
      out.write("  ~\n");
      out.write("  ~ This software is distributed in the hope that it will be useful,\n");
      out.write("  ~ but WITHOUT ANY WARRANTY; without even the implied warranty of\n");
      out.write("  ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU\n");
      out.write("  ~ Lesser General Public License for more details.\n");
      out.write("  ~\n");
      out.write("  ~ You should have received a copy of the GNU Lesser General Public\n");
      out.write("  ~ License along with this software; if not, write to the Free\n");
      out.write("  ~ Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA\n");
      out.write("  ~ 02110-1301 USA, or see the FSF site: http://www.fsf.org.\n");
      out.write("  -->\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 
session.invalidate();

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>PicketLink Example Application</title>\n");
      out.write("<META HTTP-EQUIV=\"refresh\" CONTENT=\"1;URL=");
      out.print( request.getContextPath() );
      out.write("\">\n");
      out.write("<link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\">\n");
      out.write("<link rel=\"StyleSheet\" href=\"css/idp.css\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t<img src=\"images/picketlink-banner-1180px.png\"\n");
      out.write("\t\tstyle=\"margin-top: -10px; margin-left: -10px; opacity: 0.4; filter: alpha(opacity =   40);\" />\n");
      out.write("\t<div class=\"loginBox\"\n");
      out.write("\t\tstyle=\"margin-bottom: 80px; border: 1px solid #000000; width: 440px; background-color: #F8F8F8; align: center;\">\n");
      out.write("\t\t<center>\n");
      out.write("\t\t\t<p>\n");
      out.write("\t\t\t\t<b>Logout in progress. You will be redirected to the Login Page.</b>\n");
      out.write("\t\t\t</p>\n");
      out.write("\t\t</center>\n");
      out.write("\t</div>\n");
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
