package controllers;

import listeners.UserContextListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import static security.CipherClass.*;
import logging.LoggerClass;
import model.Reservation;
import model.Reviews;

public class LoginServlet extends HttpServlet {

    Connection con;
    LoggerClass loggerClass = new LoggerClass();
    Logger logger = loggerClass.getLoggerClass();

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
            sc.setAttribute("errorMessage", "");
            try {
                if (con != null) {

                    //Initialize Variables
                    HttpSession session = request.getSession();
                    String userEmail = request.getParameter("resUserEmail").trim();
                    String pass = request.getParameter("resPass").trim();
                    String query = "SELECT * FROM ADMINACCOUNTS";
                    PreparedStatement pStmt = con.prepareStatement(query);
                    ResultSet rs = pStmt.executeQuery();

                    //Check login details if correct
                    while (rs.next()) {
                        //If the username/email and password is correct, redirect
                        if ((userEmail.equals(rs.getString("EMAIL")) || userEmail.equals(rs.getString("USERNAME")))
                                && pass.equals(decrypt(rs.getString("PASSWORD")))) {
                            session.setAttribute("sessionTest", true);
                            Admin human = new Admin(userEmail, pass);
                            sc.setAttribute("loginDetails", human);

                            UserContextListener ucl = new UserContextListener();
                            ucl.contextInitialized(new ServletContextEvent(sc));
                            logger.log(Level.INFO, rs.getString("USERNAME") + " logged in");

                            query = "SELECT * FROM RESERVATIONDB";
                            pStmt = con.prepareStatement(query);
                            rs = pStmt.executeQuery();

                            ArrayList<Reservation> reservationArray = new ArrayList<Reservation>();

                            while (rs.next()) {
                                Reservation reservation = new Reservation(rs.getInt("USERID"), rs.getInt("NUMBEROFPPL"), rs.getString("FNAME"), rs.getString("LNAME"), rs.getString("CPNUMBER"), rs.getString("EMAIL"), rs.getDate("RESERVEDDATE"));
                                reservationArray.add(reservation);
                            }
                            sc.setAttribute("reservationArray", reservationArray);

                            query = "SELECT * FROM REVIEWS";
                            pStmt = con.prepareStatement(query);
                            rs = pStmt.executeQuery();

                            ArrayList<Reviews> ReviewsArray = new ArrayList<Reviews>();

                            while (rs.next()) {
                                Reviews review = new Reviews(rs.getString("NAME"), rs.getString("COMMENT"), rs.getDate("DATE"), rs.getBoolean("ACTIVE"));
                                ReviewsArray.add(review);
                            }
                            sc.setAttribute("ReviewsArray", ReviewsArray);

                            query = "SELECT * FROM ADMINACCOUNTS";
                            pStmt = con.prepareStatement(query);
                            rs = pStmt.executeQuery();
                            ArrayList<Admin> adminArray = new ArrayList<Admin>();
                            
                            while (rs.next()) {
                                Admin admin = new Admin(rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"));
                                adminArray.add(admin);
                            }
                            sc.setAttribute("adminArray", adminArray);
                            
                            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_database.jsp");
                            dispatcher.forward(request, response);
                            return;
                        }
                    }
                    //If the username/email and password is incorrect, show error message
                    pStmt.close();
                    rs.close();
                    sc.setAttribute("errorMessage", "Sorry, your username or password was incorrect!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin_login.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (SQLException sqle) {
                sc.setAttribute("errorMessage", "SQL Exception occurred!");
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
