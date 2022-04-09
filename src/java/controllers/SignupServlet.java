package controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static security.CipherClass.*;

public class SignupServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        ServletContext sc = getServletContext();
        sc.setAttribute("errorMessage", "");
        try {
            if (con != null) {
                String username = request.getParameter("regUser").trim();
                String email = request.getParameter("regEmail").trim();
                String pass = request.getParameter("regPass").trim();
                String conpass = request.getParameter("regConfirmPass").trim();
                if (!pass.equals(conpass)) { //password checker
                    sc.setAttribute("errorMessage", "Error, your password does not match with your confirm password!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin_signup.jsp");
                    dispatcher.forward(request, response);
                    return;
                }

                String query = "SELECT USERNAME, EMAIL FROM ADMINACCOUNTS";
                PreparedStatement pStmt = con.prepareStatement(query);
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    if (email.equals(rs.getString("EMAIL"))) { //check if email exists
                        sc.setAttribute("errorMessage", "Sorry, that email is already taken! Please sign up with a different one!");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_signup.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }
                    if (username.equals(rs.getString("USERNAME"))) { //check if email exists
                        sc.setAttribute("errorMessage", "Sorry, that username is already taken! Please enter a different one!");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_signup.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }
                }
                pStmt.close();

                PreparedStatement st = con.prepareStatement("INSERT INTO ADMINACCOUNTS (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)");
                st.setString(1, username);
                st.setString(2, email);
                st.setString(3, encrypt(pass));
                st.executeUpdate();
                response.sendRedirect("admin_login.jsp");
                st.close();
                rs.close();
                return;
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException sqle) {
            sc.setAttribute("errorMessage", "SQL Exception occurred!");
            response.sendRedirect("errorPage.jsp");
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
