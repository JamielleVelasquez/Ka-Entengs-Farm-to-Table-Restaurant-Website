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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Reservation;
import security.CipherClass;
public class AdminEditServlet extends HttpServlet {

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
            String username =request.getParameter("username");

            PreparedStatement pStmt = con.prepareStatement("SELECT * FROM ADMINACCOUNTS WHERE USERNAME = ?");

            pStmt.setString(1, username);

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            if (request.getParameter("action").equals("Edit")) {

                Admin admin = new Admin( rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"));

                sc.setAttribute("admin", admin);
                RequestDispatcher dispatcher = request.getRequestDispatcher("AdminEdit_Update.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (request.getParameter("action").equals("Update")) {

                pStmt = con.prepareStatement("UPDATE ADMINACCOUNTS SET USERNAME = ?, EMAIL = ?, PASSWORD = ?");
                pStmt.setString(1, request.getParameter("username"));
                pStmt.setString(2, request.getParameter("email"));
                pStmt.setString(3, security.CipherClass.encrypt(request.getParameter("password")));
                pStmt.executeUpdate();

                String query = "SELECT * FROM ADMINACCOUNTS";
                pStmt = con.prepareStatement(query);
                rs = pStmt.executeQuery();

                ArrayList<Admin> adminArray = new ArrayList<Admin>();

                while (rs.next()) {
                    Admin admin = new Admin(rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"));
                    adminArray.add(admin);
                }
                sc.setAttribute("adminArray", adminArray);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Admin_Edit.jsp");
                dispatcher.forward(request, response);
                pStmt.close();
                return;
            }else if (request.getParameter("action").equals("Delete")){
            pStmt = con.prepareStatement("DELETE FROM ADMINACCOUNTS WHERE USERNAME = ?");
            pStmt.setString(1,request.getParameter("username"));
            pStmt.executeUpdate();
            String query = "SELECT * FROM ADMINACCOUNTS";
            pStmt = con.prepareStatement(query);
            rs = pStmt.executeQuery();

            ArrayList<Admin> adminArray = new ArrayList<Admin>();

            while (rs.next()) {
                    Admin admin = new Admin(rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"));
                    adminArray.add(admin);
            }
            sc.setAttribute("adminArray", adminArray);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Admin_Edit.jsp");
            dispatcher.forward(request, response);
            pStmt.close();
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
