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
        <h1>Hello World!
        </h1>
        <form  method="POST" action="update.do">
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
                    while (rs.next()) {%>
            <div class="masterTable">
                <div class="divDetails">
                    <input type="hidden" id="userid" name="userid" value="<%= rs.getString("USERID")%>">
                    <label for="fname">First name of client:</label><br>
                    <input type="text" id="fname" name="fname" placeholder="<%= rs.getString("FNAME")%>" required><br>

                    <label for="lname">Last name of client:</label><br>
                    <input type="text" id="lname" name="lname" placeholder="<%= rs.getString("LNAME")%>" required><br><br>


                    <div class="input-daterange m-0 w-25">
                        <input type="date" class="form-control" name="date" placeholder="Date" required>
                    </div>

                    <label for="cpnumber">Cellphone number:</label><br>
                    <input type="number" placeholder="<%= rs.getString("CPNUMBER")%>" id="cpnumber" name="number"required><br><br>

                    <label for="numofppl">Number of People:</label><br>
                    <input type="number" id="numofppl" placeholder="<%= rs.getInt("NUMBEROFPPL")%>" name="numofppl"required><br><br>

                    <label for="email">Email:</label><br>
                    <input type="email" id="email" placeholder="<%= rs.getString("EMAIL")%>" name="email"required><br><br>
                </div>
            </div>

            <%
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }%>
            <br><br>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
