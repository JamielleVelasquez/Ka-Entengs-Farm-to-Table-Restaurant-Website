package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reservation;
import model.Reviews;

public class ReviewStatusServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();

        try {

            //get name
            String name = request.getParameter("name");

            PreparedStatement pStmt = con.prepareStatement("SELECT * FROM REVIEWS WHERE CAST(NAME AS VARCHAR(128)) = ?");

            pStmt.setString(1, name);

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            if (request.getParameter("action").equals("Status")) {

                if (rs.getBoolean("ACTIVE") == false) {
                    pStmt = con.prepareStatement("UPDATE APP.REVIEWS SET ACTIVE =true WHERE CAST(NAME AS VARCHAR(128)) = ?");
                    pStmt.setString(1, name);
                    pStmt.executeUpdate();
                } else {
                    pStmt = con.prepareStatement("UPDATE APP.REVIEWS SET ACTIVE =false WHERE CAST(NAME AS VARCHAR(128)) = ?");
                    pStmt.setString(1, name);
                    pStmt.executeUpdate();
                }

                String query = "SELECT * FROM REVIEWS";
                pStmt = con.prepareStatement(query);
                rs = pStmt.executeQuery();
                ArrayList<Reviews> reviewArray = new ArrayList<Reviews>();

                while (rs.next()) {
                    Reviews review = new Reviews(rs.getString("NAME"), rs.getString("COMMENT"), rs.getDate("DATE"), rs.getBoolean("ACTIVE"));
                    reviewArray.add(review);
                }
                sc.setAttribute("ReviewsArray", reviewArray);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_review.jsp");
                dispatcher.forward(request, response);

            } else if (request.getParameter("action").equals("Delete")) {
                {
                    pStmt = con.prepareStatement("DELETE FROM REVIEWS WHERE CAST(NAME AS VARCHAR(128)) = ?");
                    pStmt.setString(1, name);
                    pStmt.executeUpdate();
                    String query = "SELECT * FROM REVIEWS";
                    pStmt = con.prepareStatement(query);
                    rs = pStmt.executeQuery();
                    ArrayList<Reviews> reviewArray = new ArrayList<Reviews>();

                    while (rs.next()) {
                        Reviews review = new Reviews(rs.getString("NAME"), rs.getString("COMMENT"), rs.getDate("DATE"), rs.getBoolean("ACTIVE"));
                        reviewArray.add(review);
                    }
                    sc.setAttribute("ReviewsArray", reviewArray);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin_review.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
