<%@page import="model.Admin"%>
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
            Admin scMsg = (Admin) getServletContext().getAttribute("loginDetails");
            String firstName = scMsg.getUsername();
        %> 
    <header-component></header-component>
    <div class="container-fluid p-3 pb-sm-0 p-sm-5 ">
        <div class="row g-0 mb-5 justify-content-center justify-content-sm-between m-0 mx-sm-5">
            <div style="width: auto;">
                <h1>Reservations</h1>
                <hr class="color-green opacity-100" style="height: 2px;">
            </div>
            <div class="text-end mx-5" style="width: auto;">
                <h6>
                    Signed in as:
                    <% out.print(firstName); %>
                </h6>
                <form action="logout.do" method="POST">
                    <button class="btn btn-primary">Log Out</button>
                </form>
            </div>
        </div>
        <div class="row g-0 justify-content-center justify-content-sm-between flex-wrap">
            <div style="width: auto;" class="p-0 m-1">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="" data-bs-toggle="dropdown">
                        Sorting by: Name
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Sorting by: Name</a></li>
                        <li><a class="dropdown-item" href="#">Sorting by: Date</a></li>
                        <li><a class="dropdown-item" href="#">Sorting by: no. of Persons</a></li>
                    </ul>
                </div>
            </div>
            <div style="width: auto;" class="p-0 m-1">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="" data-bs-toggle="dropdown">
                        Ascending
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Ascending</a></li>
                        <li><a class="dropdown-item" href="#">Descending</a></li>
                    </ul>
                </div>
            </div>
            <div style="width: auto;" class="p-0 m-1">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="" data-bs-toggle="dropdown">
                        Searching in: First Name
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Searching in: First Name</a></li>
                        <li><a class="dropdown-item" href="#">Searching in: Last Name</a></li>
                        <li><a class="dropdown-item" href="#">Searching in: Date</a></li>
                        <li><a class="dropdown-item" href="#">Searching in: Number</a></li>
                        <li><a class="dropdown-item" href="#">Searching in: Email</a></li>
                        <li><a class="dropdown-item" href="#">Searching in: no. of Persons</a></li>
                    </ul>
                </div>
            </div>
            <div style="width: auto;" class="p-0 m-1">
                <input type="text" class="form-control" placeholder="Search:">
            </div>
            <div style="width: auto;" class="p-0 m-1">
                <button class="btn btn-primary">Print PDF</button>
            </div>
        </div>

    </div>
    <div class="container-fluid p-3">
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

        %>

        <!-- One Instance of a Reservation -->
        <%int i = 1;
            while (rs.next()) {%>
        <div class="row g-0 align-items-center justify-content-center justify-content-sm-between p-1 p-sm-4 my-3 border rounded" style="border-color:darkgray; word-wrap:break-word;">


            <div class="card border-0 my-2" style="width: auto;">
                <h1 class="card-header bg-white border-0">
                    <p><%out.print(rs.getString("FNAME")); %>
                        <%out.print(rs.getString("LNAME")); %></p>
                </h1>
                <h6 class="card-subtitle py-2 px-3">
                    <p><%out.print(rs.getDate("RESERVEDDATE"));%></p>
                </h6>
            </div>
            <ul class="list-group list-group-flush my-2" style="width: auto;">
                <p><%out.print(rs.getInt("NUMBEROFPPL"));%></p>
                <p><%out.print(rs.getString("CPNUMBER"));%></p>
                <p><%out.println(rs.getString("EMAIL"));%></p>
            </ul>

            <div class=" my-2" style="width: auto;">
                <form method="POST" action="admin_update.jsp">
                    <input type="hidden" name="update<%=i%>" value="<%= rs.getString("USERID")%>">
                    <input class="btn btn-success text-white mb-3" type="submit" value="Edit">
                </form>
                <br>
                <form method="POST" action="delete.do">
                    <input type="hidden" name="delete<%=i%>" value="<%= rs.getString("USERID")%>">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">
                        Delete
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="deleteModal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    Delete this record?
                                </div>
                                <div class="modal-footer">
                                    <input class="btn btn-danger" type="submit" value="Delete">
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
            </div>

        </div>
        <%}%>
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