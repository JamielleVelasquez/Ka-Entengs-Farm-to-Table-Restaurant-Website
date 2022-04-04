package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class updateServlet extends HttpServlet {

    Connection con;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
            if (numPpl > 30) {
                sc.setAttribute("errorMessage", "Max number of people is 30!");
                throw new SQLException();
            } else if (numPpl <= 0) {
                sc.setAttribute("errorMessage", "Cannot accept less than or equal to 0 value!");
                throw new SQLException();
            }
            String email = request.getParameter("email").trim();

            //checks if fields are empty
            if (fn.isEmpty()) {
                sc.setAttribute("errorMessage", "First name field is empty!");
                throw new SQLException();
            } else if (ln.isEmpty()) {
                sc.setAttribute("errorMessage", "Last name field is empty!");
                throw new SQLException();
            } else if (numPpl == 0) {
                sc.setAttribute("errorMessage", "Number of People field is empty!");
                throw new SQLException();
            } else if (cpNum.isEmpty()) {
                sc.setAttribute("errorMessage", "Cellphone number field is empty!");
                throw new SQLException();
            } else if (email.isEmpty()) {
                sc.setAttribute("errorMessage", "Email field is empty!");
                throw new SQLException();
            } else if (date.isEmpty()) {
                sc.setAttribute("errorMessage", "Date is empty or invalid!");
                throw new SQLException();
            }
            System.out.println(userid);

            //separate if check if date is before today, you can't time travel bro
            System.out.println(inputDate);
            System.out.println(todayDate);
            if (todayDate.after(inputDate)) {
                sc.setAttribute("errorMessage", "Date entered is before today! Please enter a valid date!");
                throw new SQLException();
            }

            PreparedStatement pStmt = con.prepareStatement("UPDATE RESERVATIONDB SET FNAME = ?, LNAME = ?, "
                    + "CPNUMBER = ?, NUMBEROFPPL = ?, EMAIL = ?, RESERVEDDATE = ?"
                    + "WHERE USERID = ?");

            pStmt.setString(1, fn);
            pStmt.setString(2, ln);
            pStmt.setString(3, cpNum);
            pStmt.setInt(4, numPpl);
            pStmt.setString(5, email);
            System.out.println(new java.sql.Date(inputDate.getTime()));
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
