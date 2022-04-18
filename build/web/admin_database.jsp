<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Reservation"%>
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
        <div class="row g-0 mb-5 justify-content-center justify-content-sm-around m-0">
            <div style="width: auto;">
                <h1>Reservations</h1>
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
            <form action="pdf.do" method="get" style="width: auto;" class="p-0 m-1">
                <button class="btn btn-primary">Print PDF</button>
            </form>
                <form action="OpenRev.do" method="get" style="width: auto;" class="p-0 m-1">
                <button class="btn btn-primary">Reviews</button>
            </form>
                <form action="OpenEdit.do" method="get" style="width: auto;" class="p-0 m-1">
                <button class="btn btn-primary">Edit Admin</button>
            </form>
        </div>
        <form method="GET" action="sort.do" class="row g-0 justify-content-center justify-content-sm-between align-items-center flex-wrap">
            <div style="width: auto;" class="p-0 m-1">

                <select name="sortby" class="form-select">
                    <option value="name" selected>Sort by: Name</option>
                    <option value="date">Sort by: Date</option>
                </select>

            </div>
            <div style="width: auto;" class="p-0 m-1">

                <select name="sorthow" class="form-select">
                    <option value="asc" selected>Ascending</option>
                    <option value="dsc">Descending</option>
                </select>

            </div>

            <div style="width: auto;" class="p-0 m-1">

                <select name="selectiveDate" class="form-select">
                    <option value="all" selected>All Time</option>
                    <option value="day">Today</option>
                    <option value="week">This Week</option>
                    <option value="month">This Month</option>
                </select>

            </div>
            <div style="width: auto;" class="p-0 m-1">

                <select name="searchWhere" class="form-select mb-2">
                    <option value="name" selected>Search in: Name</option>
                    <option value="number">Search in: Number</option>
                    <option value="email">Search in: Email</option>
                    <option value="date">Search in: Date</option>
                </select>
                <input name="searchValue" type="text" class="form-control" placeholder="Search:">
            </div>

            <div style="width: auto;" class="p-0 m-1">

                <button type="submit" class="btn btn-primary">Sort / Search</button>

            </div>

        </form>

    </div>
    <div class="container-fluid p-3">
        <%
            ArrayList<Reservation> reservationArray = (ArrayList<Reservation>) getServletContext().getAttribute("reservationArray");
            Iterator<Reservation> iterator = reservationArray.iterator();

            while (iterator.hasNext()) {
                Reservation reservation = iterator.next();
        %>
        <div class="row g-0 align-items-center justify-content-center justify-content-sm-between p-1 p-sm-4 my-3 border rounded" style="border-color:darkgray; word-wrap:break-word;">


            <div class="card border-0 my-2" style="width: auto;">
                <h1 class="card-header bg-white border-0">
                    <p><%=reservation.getFirstName()%>
                        <%=reservation.getLastName()%></p>
                </h1>
                <h6 class="card-subtitle py-2 px-3">
                    <p><%=reservation.getDate()%></p>
                </h6>
            </div>
            <ul class="list-group list-group-flush my-2" style="width: auto;">
                <p><%=reservation.getGroupSize()%></p>
                <p><%=reservation.getCellNum()%></p>
                <p><%=reservation.getEmail()%></p>
            </ul>

            <div class=" my-2" style="width: auto;">
                <form method="POST" action="update.do">
                    <input type="hidden" name="userid" value="<%=reservation.getUserId()%>">
                    <input class="btn btn-success text-white mb-3" type="submit" name="action" value="Edit">
                </form>
                <br>
                <form method="POST" action="delete.do">
                    <input type="hidden" name="userid" value="<%=reservation.getUserId()%>">
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
                                    <input class="btn btn-danger" type="submit" name="action" value="Delete">
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
            </div>

        </div>
        <%}%>
    </div>
    <footer-component></footer-component>
    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>