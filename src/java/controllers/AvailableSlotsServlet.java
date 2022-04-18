package controllers;

import java.io.IOException;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class AvailableSlotsServlet extends HttpServlet {

    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date todayDate = new Date();

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
        try {

            response.setContentType("text/html;charset=UTF-8");
            ServletContext sc = getServletContext();
            sc.setAttribute("errorMessage", "");
            
            int selectedDateSlots = 30;
            Date selectedDate = new Date();

            try {
                String tempDate = request.getParameter("resDate").trim();
                selectedDate = sdf.parse(tempDate);
            } catch (NullPointerException npe) {
                response.sendRedirect("errorPage.jsp");
                npe.printStackTrace();
            } catch (ParseException pe) {
                response.sendRedirect("errorPage.jsp");
                pe.printStackTrace();
            }

            //add 3 days to current date to implement company policy
            Calendar c = Calendar.getInstance();
            c.setTime(todayDate);
            c.add(Calendar.DATE, 3);

            //update error message if the reservation is not 3 days ahead
            if (selectedDate.before(c.getTime())) {
                sc.setAttribute("errorMessage", "Date is invalid, we only allow reservations that are 3 days in advance");
                RequestDispatcher dispatcher = request.getRequestDispatcher("reservation.jsp");
                dispatcher.forward(request, response);
            }

            String query = "SELECT NUMBEROFPPL FROM RESERVATIONDB WHERE RESERVEDDATE=?";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setDate(1, new java.sql.Date(selectedDate.getTime()));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) { //loop through db
                selectedDateSlots -= rs.getInt("NUMBEROFPPL");
            }
            if(selectedDateSlots < 0)
                selectedDateSlots = 0;
            sc.setAttribute("selectedDateSlots", selectedDateSlots);
            sc.setAttribute("selectedDate", sdf.format(selectedDate));
            response.sendRedirect("reservation.jsp");
            prepStmt.close();

        } catch (SQLException sqle) {
            response.sendRedirect("errorPage.jsp");
            sqle.printStackTrace();
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
