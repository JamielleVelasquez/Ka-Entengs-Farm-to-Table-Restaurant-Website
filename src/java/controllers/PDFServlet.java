package controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logging.LoggerClass;
import model.Admin;
import model.Reservation;

public class PDFServlet extends HttpServlet {

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
        ServletContext sc = getServletContext();
        //Pdf Event Handling for Header, Footer and Page Number
        class HeaderFooterPageEvent extends PdfPageEventHelper {

            //Declare Variables
            PdfTemplate t;
            Image total;

            //Initialize Variables
            @Override
            public void onOpenDocument(PdfWriter writer, Document document) {

                t = writer.getDirectContent().createTemplate(30, 16);
                try {
                    total = Image.getInstance(t);
                    total.setRole(PdfName.ARTIFACT);

                } catch (DocumentException de) {
                    throw new ExceptionConverter(de);
                }
            }

            //Saves total page number
            @Override
            public void onCloseDocument(PdfWriter writer, Document document) {

                //Print page number on 
                ColumnText.showTextAligned(t, Element.ALIGN_LEFT,
                        new Phrase(String.valueOf(writer.getPageNumber())),
                        2, 2, 0);
            }

            //Format for Header and Footer
            public void onEndPage(PdfWriter writer, Document document) {

                //Initialize Variables
                PdfPTable table = new PdfPTable(3);
                try {

                    //Table formatting
                    table.setWidths(new int[]{24, 24, 2});
                    table.setTotalWidth(920);
                    table.getDefaultCell().setFixedHeight(20);
                    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                    //Add footer
                    table.addCell(new Phrase(request.getServletContext().getInitParameter("footer")));

                    //Formatting
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

                    //Page Number Formatting
                    table.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber())));
                    PdfPCell cell = new PdfPCell(total);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);
                    PdfContentByte canvas = writer.getDirectContent();
                    canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
                    table.writeSelectedRows(0, -1, 36, 30, canvas);
                    canvas.endMarkedContentSequence();

                } catch (DocumentException de) {
                    throw new ExceptionConverter(de);
                }

                PdfContentByte cb = writer.getDirectContent();

                //Add Header
                Font font = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
                Phrase header = new Phrase("Ka Enteng's Farm to Table Restaurant", font);

                //Header Formatting
                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                        header,
                        (document.right() - document.left()) / 2 + document.leftMargin(),
                        document.top() + 10, 0);
            }
        }

        java.util.Date date = new java.util.Date();

        // Get username from the current session
        Admin scMsg = (Admin) getServletContext().getAttribute("loginDetails");
        String username = scMsg.getUsername();

        //Create an output stream for writing binary data in the response.
        ServletOutputStream os = response.getOutputStream();

        //Set the response content type to PDF
        response.setContentType("application/pdf");

        //Get dates
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat pdfDate = new SimpleDateFormat("yyyyMMdd");

        //Save dates as string
        String pdfDateName = pdfDate.format(date);
        String strDate = dateFormat.format(date);

        //Set filename to current date
        response.setHeader("Content-Disposition", "attachment;filename=KaEntengReservations" + pdfDateName + ".pdf");

        //Create a new document
        Document doc = new Document();

        try {
            //Create an instance of the PdfWriter using the output stream
            PdfWriter writer = PdfWriter.getInstance(doc, os);

            //Create new HeaderFooterPageEvent for the header and footer
            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            writer.setPageEvent(event);

            //Document formatting
            doc.setPageSize(PageSize.LETTER.rotate());
            doc.open();

            //Add information
            doc.add(new Paragraph("PDF generated by: " + username));
            doc.add(new Paragraph("Generated on: " + strDate));
            doc.add(Chunk.NEWLINE);

            ArrayList<Reservation> reservations = (ArrayList<Reservation>) sc.getAttribute("reservationArray");
            System.out.println(reservations);

            //Create table with 7 columns
            PdfPTable my_report_table = new PdfPTable(6);
            //Create a table header
            Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPCell table_cell = new PdfPCell(new Phrase("Reservation Table", font));
            table_cell.setColspan(7);
            setCellAttributes(table_cell,
                    Rectangle.BOX,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_CENTER,
                    5);
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("User ID", font));
            setCellAttributes(table_cell,
                    Rectangle.TOP + Rectangle.LEFT + Rectangle.BOTTOM,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_CENTER,
                    5);

            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Name", font));
            setCellAttributes(table_cell,
                    Rectangle.TOP + Rectangle.BOTTOM,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_CENTER,
                    5);

            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Cellphone Number", font));
            setCellAttributes(table_cell,
                    Rectangle.TOP + Rectangle.BOTTOM,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_CENTER,
                    5);

            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Group Size", font));
            setCellAttributes(table_cell,
                    Rectangle.TOP + Rectangle.BOTTOM,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_CENTER,
                    5);

            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Email", font));
            setCellAttributes(table_cell,
                    Rectangle.TOP + Rectangle.BOTTOM,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_CENTER,
                    5);

            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Date", font));
            setCellAttributes(table_cell,
                    Rectangle.TOP + Rectangle.RIGHT + Rectangle.BOTTOM,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_CENTER,
                    5);

            my_report_table.addCell(table_cell);

            //Inputs data
            int i = 1;
            for (Reservation r : reservations) {

                table_cell = new PdfPCell(new Phrase(r.getUserId() + ""));
                setCellAttributes(table_cell, Rectangle.LEFT, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                if (i == reservations.size()) {
                    setCellAttributes(table_cell, Rectangle.LEFT + Rectangle.BOTTOM, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                }
                my_report_table.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(r.getFirstName() + " " + r.getLastName()));
                setCellAttributes(table_cell, Rectangle.NO_BORDER, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                if (i == reservations.size()) {
                    setCellAttributes(table_cell, Rectangle.BOTTOM, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                }
                my_report_table.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(r.getCellNum()));
                setCellAttributes(table_cell, Rectangle.NO_BORDER, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                if (i == reservations.size()) {
                    setCellAttributes(table_cell, Rectangle.BOTTOM, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                }
                my_report_table.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(r.getGroupSize() + ""));
                setCellAttributes(table_cell, Rectangle.NO_BORDER, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                if (i == reservations.size()) {
                    setCellAttributes(table_cell, Rectangle.BOTTOM, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                }
                my_report_table.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(r.getEmail()));
                setCellAttributes(table_cell, Rectangle.NO_BORDER, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                if (i == reservations.size()) {
                    setCellAttributes(table_cell, Rectangle.BOTTOM, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                }
                my_report_table.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(sdf.format(r.getDate())));
                setCellAttributes(table_cell, Rectangle.RIGHT, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                if (i == reservations.size()) {
                    setCellAttributes(table_cell, Rectangle.RIGHT + Rectangle.BOTTOM, Element.ALIGN_CENTER, Element.ALIGN_CENTER, 5);
                }
                my_report_table.addCell(table_cell);
                i++;
            }

            //Attach report table to PDF
            doc.add(my_report_table);
            doc.close();
        } catch (DocumentException ex) {
        }
    }

    private void setCellAttributes(PdfPCell cell, int border, int hAlign, int vAlign, int padding) {
        cell.setBorder(border);
        cell.setHorizontalAlignment(hAlign);
        cell.setVerticalAlignment(vAlign);
        cell.setPadding(padding);
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
