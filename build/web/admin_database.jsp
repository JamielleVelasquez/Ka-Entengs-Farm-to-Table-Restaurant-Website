<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

        <title>Reservations</title>
    </head>

    <body>
        <%
            if (session.getAttribute("sessionTest") == null || session == null) {
                response.sendRedirect("landing_page.html");
                return;
            }
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        %>
    <header-component></header-component>
    <div class="navbar-links">
        <ul>
            <li><a href="logout.do">Logout</a></li>
        </ul>
    </div>
    <div class="databaseDisplay">
        <table border="3" width="100%" cellspacing="2" cellpadding="2">
            <tr>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Cellphone Number</td>
                <td>Number of People</td>
                <td>Email</td>
                <!-- <td>DATE</td> --> <!-- Pag gumagana na ung DATE -->
            </tr>
            <%
                try {

                    //Register driver
                    Class.forName(getServletContext().getInitParameter("jdbcClassName"));
                    System.out.println("Loaded driver.");

                    //Use String buffer for connection
                    StringBuffer buff = new StringBuffer(getServletContext().getInitParameter("jdbcDriverURL"))
                            .append("://").append(getServletContext().getInitParameter("dbHostName"))
                            .append(":").append(getServletContext().getInitParameter("dbPort"))
                            .append("/").append(getServletContext().getInitParameter("databaseName"));
                    //jdbc:derby://localhost:1527/KaEntengRestaurantToTableDB

                    //Establish connection
                    Connection con = DriverManager.getConnection(buff.toString(),
                            getServletContext().getInitParameter("dbUserName"), getServletContext().getInitParameter("dbPassword"));
                    System.out.println("You are now connected.");
                    String query = "SELECT * FROM RESERVATIONDB";
                    PreparedStatement pStmt = con.prepareStatement(query);
                    ResultSet rs = pStmt.executeQuery();
                    while (rs.next()) {
            %>
            <tr>
                <td><%out.print(rs.getString("FNAME")); %></td>
                <td><%out.print(rs.getString("LNAME")); %></td>
                <td><%out.print(rs.getString("CPNUMBER"));%></td>
                <td><%out.print(rs.getInt("NUMBEROFPPL"));%></td>
                <td><%out.print(rs.getString("EMAIL"));%></td>
                <!-- <td><%//out.print(rs.getString("DATE"));%></td> --> <!-- Pag gumagana na ung DATE -->
            </tr>
            <%
                }
            %>
        </table>
        <%
                rs.close();
                pStmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </div>
    <footer-component></footer-component>
    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>