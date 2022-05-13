<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Reservation"%>
<%@page import="model.Admin"%>
<%@page import="model.Reviews"%>
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

        <title>Reviews</title>
    </head>

    <body>
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
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
                <h1>Reviews</h1>
            </div>
            
            <a href="admin_database.jsp" style="width: auto;" class="p-0 m-1">
                <button class="btn btn-primary">Reservations</button>
            </a>
            
            <a href="Admin_Edit.jsp" style="width: auto;" class="p-0 m-1">
                <button class="btn btn-primary">Admin Accounts</button>
            </a>




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

    </div>
    <div class="container-fluid p-3">
        <%
            ArrayList<Reviews> ReviewsArray = (ArrayList<Reviews>) getServletContext().getAttribute("ReviewsArray");
            Iterator<Reviews> iterator = ReviewsArray.iterator();

            while (iterator.hasNext()) {
                Reviews review = iterator.next();
        %>
        <div class="row g-0 align-items-center justify-content-center justify-content-sm-between p-1 p-sm-4 my-3 border rounded" style="border-color:darkgray; word-wrap:break-word;">


            <div class="card border-0 my-2" style="width: auto;">
                <h1 class="card-header bg-white border-0">
                    <p><%=review.getName()%></p>
                </h1>
                <h6 class="card-subtitle py-2 px-3">
                    <p><%=review.getDate()%></p>
                </h6>
                <h6 class="card-subtitle py-2 px-3">
                    <p>Display: <%=review.isActive()%></p>
                </h6>
            </div>
            <ul class="list-group list-group-flush my-2" style="width: auto;">
                <p><%=review.getReview()%></p>
            </ul>

            <div class=" my-2" style="width: auto;">
                <form method="POST" action="reviewstat.do" autocomplete="off">
                    <input type="hidden" name="name" value="<%=review.getName()%>">
                    <% if (!review.isActive()) { %>
                    <input class="btn btn-secondary text-white mb-3" type="submit" name="action" value="Status">
                    <% } else { %>
                    <input class="btn btn-success text-white mb-3" type="submit" name="action" value="Status">
                    <% }%>
                </form>
                <br>
                <form method="POST" action="reviewstat.do" autocomplete="off">
                    <input type="hidden" name="name" value="<%=review.getName()%>">
                    <input class="btn btn-danger" type="submit" onclick="return confirm('Are you sure?')" name="action" value="Delete">

                </form>
            </div>

        </div>
        <%}%>
    </div>
    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>