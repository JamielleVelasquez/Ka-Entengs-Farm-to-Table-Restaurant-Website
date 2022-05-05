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
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reservation;

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

            //get user id
            int userid = Integer.parseInt(request.getParameter("userid"));

            PreparedStatement pStmt = con.prepareStatement("SELECT * FROM RESERVATIONDB WHERE USERID = ?");

            pStmt.setInt(1, userid);

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            if (request.getParameter("action").equals("Edit")) {

                Reservation reservation = new Reservation(userid, rs.getInt("NUMBEROFPPL"), rs.getString("FNAME"), rs.getString("LNAME"), rs.getString("CPNUMBER"), rs.getString("EMAIL"), rs.getDate("RESERVEDDATE"));

                sc.setAttribute("reservation", reservation);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_update.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (request.getParameter("action").equals("Update")) {

                pStmt = con.prepareStatement("UPDATE RESERVATIONDB SET FNAME = ?, LNAME = ?, "
                        + "CPNUMBER = ?, NUMBEROFPPL = ?, EMAIL = ?, RESERVEDDATE = ?"
                        + "WHERE USERID = ?");
                System.out.println(request.getParameter("fname"));
                pStmt.setString(1, request.getParameter("fname"));
                pStmt.setString(2, request.getParameter("lname"));
                pStmt.setString(3, request.getParameter("number"));
                pStmt.setInt(4, Integer.parseInt(request.getParameter("numofppl")));
                pStmt.setString(5, request.getParameter("email"));

                try {
                    inputDate = sdf.parse(request.getParameter("date"));
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                pStmt.setDate(6, new java.sql.Date(inputDate.getTime()));
                pStmt.setInt(7, userid);
                pStmt.executeUpdate();

                String query = "SELECT * FROM RESERVATIONDB ORDER BY RESERVEDDATE ASC";
                pStmt = con.prepareStatement(query);
                rs = pStmt.executeQuery();

                ArrayList<Reservation> reservationArray = new ArrayList<Reservation>();

                while (rs.next()) {
                    Reservation reservation = new Reservation(rs.getInt("USERID"), rs.getInt("NUMBEROFPPL"), rs.getString("FNAME"), rs.getString("LNAME"), rs.getString("CPNUMBER"), rs.getString("EMAIL"), rs.getDate("RESERVEDDATE"));
                    reservationArray.add(reservation);
                }
                sc.setAttribute("reservationArray", reservationArray);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_database.jsp");
                dispatcher.forward(request, response);
                pStmt.close();
                return;
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
