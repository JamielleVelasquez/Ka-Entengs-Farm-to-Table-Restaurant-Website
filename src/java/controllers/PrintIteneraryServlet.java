/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.faces.facelets.util.Path.context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintIteneraryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<String> list = new ArrayList<String>();
            Scanner s = new Scanner(new File("C:/Users/jim/Documents/GitHub/Ka-Entengs-Farm-to-Table-Restaurant-Website/ReadFiles/Itenerary.txt"));
            String container = null;
            
            while (s.hasNextLine()) {
                String current = s.nextLine();
                if (current.isEmpty()) {
                    container = concatEndTags(container);
                    list.add(container);
                    container = null;
                }
                if (current.contains("AM") || current.contains("PM")) {
                    container = concatTags("col-2 col-sm-1", current);
                    container = concatBegTags("row my-3 justify-content-center", container);
                    current = s.nextLine();
                    container += concatBegTags("col-10 col-lg-6 col-sm-8", current);
                } else {
                    container += addBr(current);
                }
            }
            
                list.stream().forEach((i) -> {
                    out.println(i);
                });
           
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

    public static String concatTags(String Class, String Text) {
        String s = "<div class =\"" + Class + "\">" + Text + "</div>";
        return s;
    }

    public static String addBr(String Text) {
        String s = "<br>" + Text;
        return s;
    }

    public static String concatBegTags(String Class, String Text) {
        String s = "<div class =\"" + Class + "\">" + Text;
        return s;
    }

    public static String concatEndTags(String Text) {
        String s = Text + "</div>";
        return s;
    }
}
