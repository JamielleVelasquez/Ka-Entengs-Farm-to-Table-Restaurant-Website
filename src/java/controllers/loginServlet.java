package controllers;

import exceptions.AuthenticationException;
import exceptions.NullValueException;
import listeners.userContextListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import static security.cipher.*;

public class loginServlet extends HttpServlet {

    Connection con;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            //Register driver
            Class.forName(config.getInitParameter("jdbcClassName"));
            System.out.println("Loaded driver.");

            //Use String buffer for connection
            StringBuffer buff = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://").append(config.getInitParameter("dbHostName"))
                    .append(":").append(config.getInitParameter("dbPort"))
                    .append("/").append(config.getInitParameter("databaseName"));
            //jdbc:derby://localhost:1527/KaEntengRestaurantToTableDB

            //Establish connection
            con = DriverManager.getConnection(buff.toString(),
                    config.getInitParameter("dbUserName"), config.getInitParameter("dbPassword"));
            System.out.println("You are now connected.");

        } catch (SQLException sql) {
            System.out.println("SQL Exception occurred.");
            sql.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class not found Exception occurred.");
            cnfe.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ServletContext sc = getServletContext();
            try {
                if (con != null) {
                    HttpSession session = request.getSession();
                    String userEmail = request.getParameter("resUserEmail").trim();
                    String pass = request.getParameter("resPass").trim();
                    if (userEmail.isEmpty() && pass.isEmpty()) {
                        sc.setAttribute("errorMessage", "Both email and password is empty!");
                        throw new NullValueException();
                    } else if (userEmail.isEmpty()) {
                        sc.setAttribute("errorMessage", "Entered username or email is empty!");
                        throw new NullValueException();
                    } else if (pass.isEmpty()) {
                        sc.setAttribute("errorMessage", "Entered password is empty!");
                        throw new NullValueException();
                    }
                    String query = "SELECT * FROM ADMINACCOUNTS";
                    PreparedStatement pStmt = con.prepareStatement(query);
                    ResultSet rs = pStmt.executeQuery();
                    while (rs.next()) {
                        if ((userEmail.equals(rs.getString("EMAIL")) || userEmail.equals(rs.getString("USERNAME")))
                                && pass.equals(decrypt(rs.getString("PASSWORD")))) { //add encryption?
                            session.setAttribute("sessionTest", true);
                            Admin human = new Admin(userEmail, pass);
                            sc.setAttribute("loginDetails", human);

                            userContextListener ucl = new userContextListener();
                            ucl.contextInitialized(new ServletContextEvent(sc));
                            response.sendRedirect("admin_database.jsp");
                            return;
                        } else if (userEmail.equals(rs.getString("EMAIL")) && !pass.equals(decrypt(rs.getString("PASSWORD")))) {
                            //error 2 - correct email, wrong pass
                            sc.setAttribute("errorMessage", "Sorry, you entered the wrong password!");
                            throw new AuthenticationException();
                        }
                    }
                    pStmt.close();
                    rs.close();
                    sc.setAttribute("errorMessage", "Sorry, the email does not exist in our database!");
                    throw new AuthenticationException();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (SQLException sqle) {
                sc.setAttribute("errorMessage", "SQL Exception occurred!");
                response.sendRedirect("errorPage.jsp");
            } catch (NullValueException nve) {
                response.sendRedirect("errorPage.jsp");
            } catch (AuthenticationException aue) {
                response.sendRedirect("errorPage.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
