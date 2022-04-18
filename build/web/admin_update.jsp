<%@page import="model.Reservation"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
        <!-- jquery for bootstrap datepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- bootstrap datepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" integrity="sha512-TQQ3J4WkE/rwojNFo6OJdyu6G8Xe9z8rMrlF9y7xpFbQfW5g8aSWcygCQ4vqRiJqFsDsE1T6MoAOMJkFXlrI9A==" crossorigin="anonymous"
              referrerpolicy="no-referrer" />
        <title>Update Record</title>
    </head>
    <body>
        <%
            if (session.getAttribute("sessionTest") == null || session == null) {
                response.sendRedirect("landing_page.jsp");
                return;
            }
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        %> 
        <div class="container-fluid p-4 p-lg-5">
            <div class="display-4 reserveLabel">Update Record</div>
            <div class="row justify-content-center">
                <div class="col-12 p-1 p-lg-5">
                    <%
                        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Reservation reservation = (Reservation) getServletContext().getAttribute("reservation");
                    %>
                    <form action="update.do" method="POST" >
                        <input type="hidden" id="userid" name="userid" value="<%=reservation.getUserId()%>">
                        <div class="row g-0 m-0 my-4">
                            <!-- input field for amount of people -->
                            <input type="number" class="form-control w-50" name="numofppl" value="<%=reservation.getGroupSize()%>" required min="1" max="30" value="1">
                            <!-- input field for date -->
                            <div class="input-daterange m-0 w-50" >
                                <input type="text" class="form-control" name="date" value="<%=sdf.format(reservation.getDate())%>" required>
                            </div>
                        </div>

                        <!-- input field for username -->
                        <input type="text" class="flName form-control p-2 m-0 my-4" name="fname" value="<%=reservation.getFirstName()%>" required>
                        <input type="text" class="flName form-control p-2 m-0 my-4" name="lname" value="<%=reservation.getLastName()%>" required>
                        <input type="number" class="userpass form-control p-2 m-0 my-4" name="number" value="<%=reservation.getCellNum()%>" required>
                        <input type="email" class="userpass form-control p-2 m-0 my-4" name="email" value="<%=reservation.getEmail()%>" required>
                        <!-- button to submit inputs -->
                        <button type="submit" name="action" value="Update" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="script.js" type="text/javascript" defer></script>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
