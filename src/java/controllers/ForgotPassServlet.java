package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static security.CipherClass.*;

public class ForgotPassServlet extends HttpServlet {

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
            try {
                String email = request.getParameter("adminEmail");
                String strQuery = "SELECT PASSWORD FROM ADMINACCOUNTS WHERE EMAIL='" + email + "'";
                PreparedStatement pStmt = con.prepareStatement(strQuery);
                ResultSet rs = pStmt.executeQuery();
                rs.next();
                String password = decrypt(rs.getString(1));
                if (password != null) {
                    String receiver = email;
                    String from = "kaEntengsAdminTest";

                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.mailtrap.io");
                    props.put("mail.smtp.port", "2525");
                    props.put("mail.smtp.auth", "true");

                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {

                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("ebfcf7c7036a91", "91b19e43b09fa6"); //create account in mailtrap.io for unique keys
                        }
                    });

                    //compose the message  
                    try {
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(from));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
                        message.setSubject("Ka-Enteng's Forgot Password Test");
                        message.setText("Hey! Your password is " + password + ".");
                        message.saveChanges();

                        // Send message  
                        Transport.send(message);
                        System.out.println("Email sent successfully!");

                    } catch (MessagingException mex) {
                        mex.printStackTrace();
                    }
                    /*mail code
                        paste your mail code here
                        ------------------
                    Mail code*/
                    out.println("Your password has been sent to your email successfully!");
                    response.sendRedirect("admin_login.jsp");
                    return;
                } else {
                    out.println("Invalid email provided!");
                }
            } catch (Exception e) {
                e.printStackTrace();
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
