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
        <div class="container-fluid p-4 p-lg-5">
            <div class="display-4 reserveLabel">Update Record</div>
            <div class="row justify-content-center">
                <div class="col-12 p-1 p-lg-5">
                    <%
                        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
                            //test comment

                            //Establish connection
                            Connection con = DriverManager.getConnection(buff.toString(),
                                    getServletContext().getInitParameter("dbUserName"), getServletContext().getInitParameter("dbPassword"));
                            System.out.println("You are now connected.");
                            PreparedStatement prepStmt = con.prepareStatement("SELECT * FROM RESERVATIONDB", ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
                            ResultSet rss = prepStmt.executeQuery();
                            rss.last();
                            String updateBT = "";
                            PreparedStatement pStmt = con.prepareStatement("SELECT * FROM RESERVATIONDB WHERE USERID = ?");
                            for (int i = 1; i <= rss.getRow(); i++) {
                                updateBT = request.getParameter("update" + i);
                                if (updateBT != null) {
                                    System.out.println(updateBT);
                                    pStmt.setInt(1, Integer.parseInt(updateBT));
                                }
                            }
                            ResultSet rs = pStmt.executeQuery();
                            while (rs.next()) {
                                Date date = (Date) rs.getObject("RESERVEDDATE");
                    %>
                    <form action="update.do" method="POST" >
                        <input type="hidden" id="userid" name="userid" value="<%= rs.getString("USERID")%>">
                        <div class="row g-0 m-0 my-4">
                            <!-- input field for amount of people -->
                            <input type="number" class="form-control w-50" name="numofppl" value="<%= rs.getInt("NUMBEROFPPL")%>" required min="1" max="30" value="1">
                            <!-- input field for date -->
                            <div class="input-daterange m-0 w-50" >
                                <input type="text" class="form-control" name="date" value="<%= sdf.format(date)%>" required>
                            </div>
                        </div>

                        <!-- input field for username -->
                        <input type="text" class="flName form-control p-2 m-0 my-4" name="fname" value="<%= rs.getString("FNAME")%>" required>
                        <input type="text" class="flName form-control p-2 m-0 my-4" name="lname" value="<%= rs.getString("LNAME")%>" required>
                        <input type="number" class="userpass form-control p-2 m-0 my-4" name="number" value="<%= rs.getString("CPNUMBER")%>" required>
                        <input type="email" class="userpass form-control p-2 m-0 my-4" name="email" value="<%= rs.getString("EMAIL")%>" required>
                        <!-- button to submit inputs -->
                        <button type="submit" class="reserveBT btn btn-primary">Update</button>
                    </form>
                    <%
                            }
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }%>
                </div>
            </div>
        </div>
        <script src="script.js" type="text/javascript" defer></script>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
