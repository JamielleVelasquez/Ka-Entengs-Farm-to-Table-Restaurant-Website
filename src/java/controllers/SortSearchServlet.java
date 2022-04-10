package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logging.LoggerClass;
import model.Reservation;

public class SortSearchServlet extends HttpServlet {

    Connection con;
    LoggerClass loggerClass = new LoggerClass();
    Logger logger = loggerClass.getLoggerClass();
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

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

        String sortedBy = request.getParameter("sortby");
        String sortHow = request.getParameter("sorthow");
        String selectiveDate = request.getParameter("selectiveDate");
        String searchWhere = request.getParameter("searchWhere");
        String searchValue = request.getParameter("searchValue");

        String orderBy = "FNAME";
        String where = "";

        try {

            switch (sortedBy) {
                case "name":
                    orderBy = "FNAME";
                    break;
                case "date":
                    orderBy = "RESERVEDDATE";
                    break;
            }

            switch (sortHow) {
                case "asc":
                    orderBy = orderBy.concat(" ASC");
                    break;
                case "dsc":
                    orderBy = orderBy.concat(" DESC");
                    break;
            }

            Date todayDate = Date.valueOf(LocalDate.now());
            Date weekDate = Date.valueOf(LocalDate.now().plusWeeks(1));
            Date monthDate = Date.valueOf(LocalDate.now().plusMonths(1));
            java.sql.Date todaySqlDate = new java.sql.Date(todayDate.getTime());
            java.sql.Date weekSqlDate = new java.sql.Date(weekDate.getTime());
            java.sql.Date monthSqlDate = new java.sql.Date(monthDate.getTime());

            switch (selectiveDate) {
                case "all":
                    break;
                case "day":
                    where = "RESERVEDDATE = '" + todaySqlDate + "'";
                    break;
                case "week":
                    where = "RESERVEDDATE >= '" + todaySqlDate + "' AND RESERVEDDATE < '" + weekSqlDate + "'";
                    break;
                case "month":
                    where = "RESERVEDDATE >= '" + todaySqlDate + "' AND RESERVEDDATE < '" + monthSqlDate + "'";

                    break;
            }

            if (!searchValue.isEmpty()) {
                if (!where.isEmpty()) {
                    switch (searchWhere) {
                        case "name":
                            where = where.concat(" AND FNAME = ?");
                            break;
                        case "number":
                            where = where.concat(" AND CPNUMBER = ?");
                            break;
                        case "email":
                            where = where.concat(" AND EMAIL = ?");
                            break;
                        case "date":
                            where = where.concat(" AND RESERVEDDATE = ?");
                            break;
                    }
                }
                switch (searchWhere) {
                    case "name":
                        where = "FNAME = ?";
                        break;
                    case "number":
                        where = "CPNUMBER = ?";
                        break;
                    case "email":
                        where = "EMAIL = ?";
                        break;
                    case "date":
                        where = "RESERVEDDATE = ?";
                        break;
                }
            }

            String query;
            PreparedStatement pStmt;
            ResultSet rs;

            query = "SELECT * FROM RESERVATIONDB"
                    + " WHERE " + where
                    + " ORDER BY " + orderBy;

            if (where.isEmpty()) {
                query = "SELECT * FROM RESERVATIONDB"
                        + " ORDER BY " + orderBy;
            }

            System.out.println(query);

            pStmt = con.prepareStatement(query);

            if (!searchValue.isEmpty()) {
                pStmt.setString(1, searchValue);
            }

            rs = pStmt.executeQuery();

            ArrayList<Reservation> reservationArray = new ArrayList<Reservation>();

            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getInt("USERID"), rs.getInt("NUMBEROFPPL"), rs.getString("FNAME"), rs.getString("LNAME"), rs.getString("CPNUMBER"), rs.getString("EMAIL"), rs.getDate("RESERVEDDATE"));
                reservationArray.add(reservation);
            }
            sc.setAttribute("reservationArray", reservationArray);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_database.jsp");
            dispatcher.forward(request, response);
            return;

        } catch (SQLException sqle) {
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
