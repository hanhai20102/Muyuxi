/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-02-26 08:32:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.errorPage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _404_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>woody 404 for Website Template for free | Home :: w3layouts</title>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\r\n");
      out.write("    <link href='http://fonts.googleapis.com/css?family=Amarante' rel='stylesheet' type='text/css'>\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("        body{\r\n");
      out.write("            background:url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/image/bg.png);\r\n");
      out.write("            margin:0;\r\n");
      out.write("        }\r\n");
      out.write("        .wrap{\r\n");
      out.write("            margin:0 auto;\r\n");
      out.write("            width:1000px;\r\n");
      out.write("        }\r\n");
      out.write("        .logo{\r\n");
      out.write("            text-align:center;\r\n");
      out.write("        }\r\n");
      out.write("        .logo p span{\r\n");
      out.write("            color:lightgreen;\r\n");
      out.write("        }\r\n");
      out.write("        .sub a{\r\n");
      out.write("            color:white;\r\n");
      out.write("            background:rgba(0,0,0,0.3);\r\n");
      out.write("            text-decoration:none;\r\n");
      out.write("            padding:5px 10px;\r\n");
      out.write("            font-size:13px;\r\n");
      out.write("            font-family: arial, serif;\r\n");
      out.write("            font-weight:bold;\r\n");
      out.write("        }\r\n");
      out.write("        .footer{\r\n");
      out.write("            color:#555;\r\n");
      out.write("            position:absolute;\r\n");
      out.write("            right:10px;\r\n");
      out.write("            bottom:10px;\r\n");
      out.write("            font-weight:bold;\r\n");
      out.write("            font-family:arial, serif;\r\n");
      out.write("        }\r\n");
      out.write("        .footer a{\r\n");
      out.write("            font-size:16px;\r\n");
      out.write("            color:#ff4800;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/image/label.png\"/>\r\n");
      out.write("<div class=\"wrap\">\r\n");
      out.write("    <div class=\"logo\">\r\n");
      out.write("        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/static/image/woody-404.png\"/>\r\n");
      out.write("        <div class=\"sub\">\r\n");
      out.write("            <p><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">Go Back to Home</a></p>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"footer\">\r\n");
      out.write("    Design by-Muyuxi\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
