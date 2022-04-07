package controllers;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReservationServlet extends HttpServlet {

    Connection con;
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date todayDate = new Date();
    Date inputDate = null;

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
                    String fn = request.getParameter("resFn").trim();
                    String ln = request.getParameter("resLn").trim();
                    String cpNum = request.getParameter("resNum").trim();
                    String date = request.getParameter("resDate").trim();

                    try {
                        inputDate = sdf.parse(date);
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }
                    int numPpl = Integer.parseInt(request.getParameter("resNumPpl"));
                    
                    String email = request.getParameter("resEmail").trim();

                    System.out.println(todayDate);
                    System.out.println(inputDate);
                    
                    //add 3 days to current date to implement company policy
                    Calendar c = Calendar.getInstance();
                    c.setTime(todayDate);
                    c.add(Calendar.DATE, 3);
                    System.out.println(c.getTime());
                    
                    //update error message if the reservation is not 3 days ahead
                    if (inputDate.before(c.getTime())) {
                        sc.setAttribute("errorMessage", "Date is invalid, we only allow reservations that are 3 days in advance");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("reservation.jsp");
                        dispatcher.forward(request, response);
                    }
                    
                    String query = "SELECT * FROM RESERVATIONDB";
                    PreparedStatement prepStmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = prepStmt.executeQuery();
                    Random rnd = new Random();
                    int userid = rnd.nextInt(99999);
                    if (rs.next()) { //check if there exists record
                        while (rs.next()) { //loop through db
                            if (rs.getInt("USERID") == userid) { //if found userid same as random number, add 1 to random number
                                System.out.println("in if");
                                userid += 1;
                            }
                        }
                    }
                    PreparedStatement pStmt = con.prepareStatement("INSERT INTO RESERVATIONDB (USERID, FNAME, LNAME, CPNUMBER, NUMBEROFPPL, EMAIL, RESERVEDDATE)"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)");
                    pStmt.setInt(1, userid);
                    pStmt.setString(2, fn);
                    pStmt.setString(3, ln);
                    pStmt.setString(4, cpNum);
                    pStmt.setInt(5, numPpl);
                    pStmt.setString(6, email);
                    pStmt.setDate(7, new java.sql.Date(inputDate.getTime()));

                    pStmt.executeUpdate();
                    response.sendRedirect("tour_info.html");
                    pStmt.close();
                    return;
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (SQLException sqle) {
                response.sendRedirect("errorPage.jsp");
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
