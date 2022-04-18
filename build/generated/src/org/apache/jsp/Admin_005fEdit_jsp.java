package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.sun.faces.facelets.compiler.CompilationMessageHolder;
import java.util.Iterator;
import java.util.ArrayList;
import model.Reservation;
import model.Admin;
import model.Reviews;
import java.sql.*;
import java.io.PrintWriter;

public final class Admin_005fEdit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("        <!-- Bootstrap CSS -->\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css\">\n");
      out.write("\n");
      out.write("        <title>Edit Admin Details</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");

            if (session.getAttribute("sessionTest") == null || session == null) {
                response.sendRedirect("landing_page.jsp");
                return;
            }
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
            Admin scMsg = (Admin) getServletContext().getAttribute("loginDetails");
            String firstName = scMsg.getUsername();
            
        
      out.write(" \n");
      out.write("    <header-component></header-component>\n");
      out.write("    <div class=\"container-fluid p-3 pb-sm-0 p-sm-5 \">\n");
      out.write("        <div class=\"row g-0 mb-5 justify-content-center justify-content-sm-around m-0\">\n");
      out.write("            <div style=\"width: auto;\">\n");
      out.write("                <h1>Admins</h1>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"text-end mx-5\" style=\"width: auto;\">\n");
      out.write("                <h6>\n");
      out.write("                    Signed in as:\n");
      out.write("                    ");
 out.print(firstName); 
      out.write("\n");
      out.write("                </h6>\n");
      out.write("                <form action=\"logout.do\" method=\"POST\">\n");
      out.write("                    <button class=\"btn btn-primary\">Log Out</button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("                <form action=\"OpenRev.do\" method=\"get\" style=\"width: auto;\" class=\"p-0 m-1\">\n");
      out.write("                <button class=\"btn btn-primary\">Reviews</button>\n");
      out.write("            </form>\n");
      out.write("               <form action=\"admin_database.jsp\" method=\"get\" style=\"width: auto;\" class=\"p-0 m-1\">\n");
      out.write("                <button class=\"btn btn-primary\">Reservation</button>\n");
      out.write("            </form>  \n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <div class=\"container-fluid p-3\">\n");
      out.write("        ");

            ArrayList<Admin> adminArray = (ArrayList<Admin>) getServletContext().getAttribute("adminArray");
            Iterator<Admin> iterator = adminArray.iterator();

            while (iterator.hasNext()) {
          
                Admin adminu = iterator.next();
               
        
      out.write("\n");
      out.write("        <div class=\"row g-0 align-items-center justify-content-center justify-content-sm-between p-1 p-sm-4 my-3 border rounded\" style=\"border-color:darkgray; word-wrap:break-word;\">\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"card border-0 my-2\" style=\"width: auto;\">\n");
      out.write("                <h1 class=\"card-header bg-white border-0\">\n");
      out.write("                    <p>");
      out.print(adminu.getEmail());
      out.write("</p>\n");
      out.write("                </h1>\n");
      out.write("                <h6 class=\"card-subtitle py-2 px-3\">\n");
      out.write("                    <p>");
      out.print(adminu.getUsername());
      out.write("</p>\n");
      out.write("                </h6>\n");
      out.write("                 <h6 class=\"card-subtitle py-2 px-3\">\n");
      out.write("                    <p>");
      out.print(adminu.getPassword());
      out.write("</p>\n");
      out.write("                </h6>\n");
      out.write("            </div>\n");
      out.write("           \n");
      out.write("\n");
      out.write("            <div class=\" my-2\" style=\"width: auto;\">\n");
      out.write("                <form method=\"POST\" action=\"AdminEdit.do\">\n");
      out.write("                    <input type=\"hidden\" name=\"username\" value=\"");
      out.print(adminu.getUsername());
      out.write("\">\n");
      out.write("                    <input class=\"btn btn-success text-white mb-3\" type=\"submit\" name=\"action\" value=\"Edit\">\n");
      out.write("                </form>\n");
      out.write("                <br>\n");
      out.write("                <form method=\"POST\" action=\"AdminEdit.do\">\n");
      out.write("                    <input type=\"hidden\" name=\"username\" value=\"");
      out.print(adminu.getUsername());
      out.write("\">\n");
      out.write("                    <!-- Button trigger modal -->\n");
      out.write("                    <button type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteModal\">\n");
      out.write("                        remove\n");
      out.write("                    </button>\n");
      out.write("\n");
      out.write("                    <!-- Modal -->\n");
      out.write("                    <div class=\"modal fade\" id=\"deleteModal\" tabindex=\"-1\">\n");
      out.write("                        <div class=\"modal-dialog\">\n");
      out.write("                            <div class=\"modal-content\">\n");
      out.write("                                <div class=\"modal-body\">\n");
      out.write("                                    Delete this record?\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-footer\">\n");
      out.write("                                    <input class=\"btn btn-danger\" type=\"submit\" name=\"action\" value=\"Delete\">\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <footer-component></footer-component>\n");
      out.write("    <script src=\"script.js\" type=\"text/javascript\" defer></script>\n");
      out.write("    <!-- Bootstrap JS -->\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
