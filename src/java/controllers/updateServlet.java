package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateServlet extends HttpServlet {

    Connection con;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
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
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        try {
            int userid = Integer.parseInt(request.getParameter("userid"));
            String fn = request.getParameter("fname").trim();
            String ln = request.getParameter("lname").trim();
            String cpNum = request.getParameter("number").trim();
            String date = request.getParameter("date").trim();

            try {
                inputDate = sdf.parse(date);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }

            int numPpl = Integer.parseInt(request.getParameter("numofppl"));

            String email = request.getParameter("email").trim();

            //add 3 days to current date to implement company policy
            Calendar c = Calendar.getInstance();
            c.setTime(todayDate);
            c.add(Calendar.DATE, 3);

            //check how many slots are available for the date chosen
            int selectedDateSlots = 30;

            String query = "SELECT NUMBEROFPPL FROM RESERVATIONDB WHERE RESERVEDDATE=?";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setDate(1, new java.sql.Date(inputDate.getTime()));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) { //loop through db
                selectedDateSlots -= rs.getInt("NUMBEROFPPL");
            }
            prepStmt.close();
            
            //update error message if the reservation is not 3 days ahead or over the max 30 person policy
            if (inputDate.before(c.getTime())) {
                sc.setAttribute("errorMessage", "Your edited date was less than 3 days in advance");
                RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (selectedDateSlots - numPpl < 0) {
                sc.setAttribute("errorMessage", "Your edited number of people is too big for that date");
                RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
                dispatcher.forward(request, response);
                return;
            }
            PreparedStatement pStmt = con.prepareStatement("UPDATE RESERVATIONDB SET FNAME = ?, LNAME = ?, "
                    + "CPNUMBER = ?, NUMBEROFPPL = ?, EMAIL = ?, RESERVEDDATE = ?"
                    + "WHERE USERID = ?");

            pStmt.setString(1, fn);
            pStmt.setString(2, ln);
            pStmt.setString(3, cpNum);
            pStmt.setInt(4, numPpl);
            pStmt.setString(5, email);
            pStmt.setDate(6, new java.sql.Date(inputDate.getTime()));
            pStmt.setInt(7, userid);

            pStmt.executeUpdate();
            pStmt.close();
            response.sendRedirect("admin_database.jsp");
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
