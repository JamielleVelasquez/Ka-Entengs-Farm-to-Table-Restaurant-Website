package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class reservationServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            ServletContext sc = getServletContext();
            try {
                if (con != null) {
                    String fn = request.getParameter("resFn").trim();
                    String ln = request.getParameter("resLn").trim();
                    int cpNum = Integer.parseInt(request.getParameter("resNum"));
                    String numPpl = request.getParameter("resNumPpl");
                    String email = request.getParameter("resEmail").trim();

                    //checks if fields are empty
                    if (fn.isEmpty()) {
                        sc.setAttribute("errorMessage", "First name field is empty!");
                        throw new SQLException(); 
                    } else if (ln.isEmpty()) {
                        sc.setAttribute("errorMessage", "Last name field is empty!");
                        throw new SQLException(); 
                    } else if (numPpl.isEmpty()) {
                        sc.setAttribute("errorMessage", "Number of People field is empty!");
                        throw new SQLException(); 
                    } else if (cpNum == 0) {
                        sc.setAttribute("errorMessage", "Cellphone number field is empty!");
                        throw new SQLException(); 
                    } else if (email.isEmpty()) {
                        sc.setAttribute("errorMessage", "Email field is empty!");
                        throw new SQLException(); 
                    } /*else if (date.isEmpty()?) {
                        sc.setAttribute("errorMessage", "Date is empty or invalid!");
                        throw new SQLException(); 
                    } */ 

                    PreparedStatement pStmt = con.prepareStatement("INSERT INTO RESERVATIONDB (FNAME, LNAME, CPNUMBER, NUMBEROFPPL, EMAIL) "
                            + "VALUES (?, ?, ?, ?, ?)");
                    pStmt.setString(1, fn);
                    pStmt.setString(2, ln);
                    pStmt.setInt(3, cpNum);
                    pStmt.setString(4, numPpl);
                    pStmt.setString(5, email);

                    pStmt.executeUpdate();
                    response.sendRedirect("tour_info.html");
                    pStmt.close();
                    return;
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
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
