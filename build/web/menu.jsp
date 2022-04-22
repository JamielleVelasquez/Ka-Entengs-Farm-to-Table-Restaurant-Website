<%@page import="java.util.Scanner"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.io.File"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileInputStream"%>
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

        <title>Menu</title>
    </head>

    <body>
    <header-component></header-component>
        <%
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
            session = null;
        %>
    <div class="row p-3 p-sm-5 g-0 justify-content-evenly align-items-center">
        <div class="col-12 col-md-4">
            <div class="display-4 fw-bold">Freshness Served in every Plate</div>
            <br>
            <a  href =#menu ><button class="btn btn-primary">Our Menu</button></a>
        </div>
        <img class="col-4 d-none d-md-block rounded" src="img/menu-banner.jpg" alt="">
    </div>
    <div class="row px-3 px-sm-5 pb-5 g-0 justify-content-evenly">
        <div class="card col-12 col-md-3 my-3 m-md-0">
            <img src="img/menu-1.jpg" class="card-img-top" alt="">
            <h5 class="card-title m-0 p-3">Fresh Farm Produce Ensalada Native Salad</h5>
        </div>
        <div class="card col-12 col-md-3 my-3 m-md-0">
            <img src="img/menu-2.jpg" class="card-img-top" alt="">
            <h5 class="card-title m-0 p-3">Vegetarian Burgers</h5>
        </div>
        <div class="card col-12 col-md-3 my-3 m-md-0">
            <img src="img/menu-3.jpg" class="card-img-top" alt="">
            <h5 class="card-title m-0 p-3">Patola or Pumpkin & Bean Soup</h5>
        </div>
    </div>
    <div id="menu" class="container-fluid bg-ivory" style="line-height: 2rem;">
        <div class="col-11 col-sm-8 col-lg-6 mx-auto py-5">
            <%
                try {
                    String line = "";
                    Scanner scan = new Scanner(new File(getServletContext().getRealPath("/").replace('\\', '/')
                            + "ReadFiles/Menu.txt"));
                    line = scan.nextLine();
            %>
            <h1 class="text-center fw-bold">Menu</h1>
            <hr class="col-12 mx-auto color-green opacity-100 mb-5" style="height: 2px;">

            <h4>BREAKFAST</h4>
            <%
                if (line.equals("BREAKFAST")) {
                    line = scan.nextLine();
                } %>
            <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
            <%
                while (!line.equals("APPETIZER")) {
            %>
            <%out.println(line);%>
            <br> <%line = scan.nextLine();
                }
            %>

            <h4>APPETIZER</h4>
            <%
                if (line.equals("APPETIZER")) {
                    line = scan.nextLine();
                } %>
            <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
            <%
                while (!line.equals("SOUP")) {
            %>
            <%out.println(line);%>
            <br> <%line = scan.nextLine();
                }
            %>

            <div class="row">
                <div class=" col-12 col-sm-6">
                    <h4>SOUP</h4>
                    <%
                        if (line.equals("SOUP")) {
                            line = scan.nextLine();
                        } %>
                    <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
                    <%
                        while (!line.equals("SALAD")) {
                    %>
                    <%out.println(line);%>
                    <% line = scan.nextLine();
                        }
                    %>
                </div>
                <div class=" col-12 col-sm-6">
                    <h4>SALAD</h4>
                    <%
                        if (line.equals("SALAD")) {
                            line = scan.nextLine();
                        } %>
                    <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
                    <%
                        while (!line.contains("MAIN MENU")) {
                    %>
                    <%out.println(line);%>
                    <% line = scan.nextLine();
                        }
                    %>
                </div>
            </div>

            <h4>MAIN MENU <small>*served with brown rice</small> </h4>
            <%
                if (line.contains("MAIN MENU")) {
                    line = scan.nextLine();
                } %>
            <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
            <%
                while (!line.equals("DESSERT")) {
            %>
            <%out.println(line);%>
            <br>
            <% line = scan.nextLine();
                }
            %>

            <h4>DESSERT</h4>
            <%
                if (line.equals("DESSERT")) {
                    line = scan.nextLine();
                } %>
            <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
            <%
                while (!line.equals("DRINKS")) {
            %>
            <%out.println(line);%>
            <br>
            <% line = scan.nextLine();
                }
            %>

            <h4>DRINKS</h4>
            <%
                if (line.equals("DRINKS")) {
                    line = scan.nextLine();
                } %>
            <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
            <%
                while (!line.equals("RICE")) {
            %>
            <%out.println(line);%>
            <br><% line = scan.nextLine();
                }
            %>

            <div class="row">
                <div class=" col-12 col-sm-6">
                    <h4>RICE</h4>
                    <%
                        if (line.equals("RICE")) {
                            line = scan.nextLine();
                        } %>
                    <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
                    <%
                        while (!line.equals("OTHERS")) {
                    %>
                    <%out.println(line);%>
                    <br><% line = scan.nextLine();
                        }
                    %>
                </div>
                <div class=" col-12 col-sm-6">
                    <h4>OTHERS</h4>
                    <%
                        if (line.equals("OTHERS")) {
                            line = scan.nextLine();
                        } %>
                    <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
                    <%
                        while (!line.equals("MERIENDA CENA")) {
                    %>
                    <%out.println(line);%>
                    <br><% line = scan.nextLine();
                        }
                    %>
                </div>
            </div>
            <div class="row">
                <div class="my-4 col-12 col-sm-6">
                    <div class="card">
                        <div class="card-body p-4">
                            <h4 class="card-title p-0">MERIENDA CENA</h4>
                            <%
                                if (line.equals("MERIENDA CENA")) {
                                    line = scan.nextLine();
                                }
                            %>
                            <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
                            <%
                                while (!line.equals("SPECIALS")) {
                            %>
                            <p class="card-text"><%out.println(line);%></p>
                            <% line = scan.nextLine();
                                }
                            %>
                        </div>
                    </div>
                </div>
                <div class="my-4 col-12 col-sm-6">
                    <div class="card">
                        <div class="card-body p-4">
                            <h4 class="card-title p-0">SPECIALS</h4>
                            <%
                                if (line.equals("SPECIALS")) {
                                    line = scan.nextLine();
                                } %>
                            <hr class="col-12 m-0 mb-3 color-green opacity-100" style="height: 2px;">
                            <%
                                while (!line.isEmpty()) {
                            %>

                            <p class="card-text"><%out.println(line);%></p>
                            <% line = scan.nextLine();
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
            <%scan.close();
                } catch (Exception io) {
                    io.printStackTrace();
                                }%>
        </div>
    </div>

    <footer-component></footer-component>
    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>