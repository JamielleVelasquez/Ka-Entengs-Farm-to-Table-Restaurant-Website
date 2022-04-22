<%@page import="model.Admin"%>

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

        <title>Admin Login</title>
    </head>

    <body>
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>

        <%
            if (session.getAttribute("sessionTest") == null || session == null) {
                response.sendRedirect("landing_page.jsp");
                return;
            }
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        %> 

    <header-component></header-component>
    <div class="row align-items-center g-0" style="height: 80vh;">
        <div class="loginBox col-12 p-3 col-sm-9 col-lg-6 mx-auto text-center">
            <div class="loginLabel display-3">Sign Up</div>
            <hr class="w-25 mx-auto mb-5 color-green opacity-100" style="height: 2px;">
            <form action="signup.do" method="POST" autocomplete="off">
                <!-- input field for username -->
                <div class="userEmail">
                    <input type="text" class="uNameEmail form-control mx-auto m-4" name="regEmail" placeholder="Email" required>
                </div>
                <div class="userName">
                    <input type="text" class="uNameEmail form-control mx-auto m-4" name="regUser" placeholder="Username" required>
                </div>
                <input type="password" class="userPass form-control mx-auto m-4" name="regPass" placeholder="Password" required>
                <input type="password" class="userPass form-control mx-auto m-4" name="regConfirmPass" placeholder="Confirm Password" required>
                <br>
                <!-- error messages go here -->
                <p class="text-danger">${errorMessage}</p>
                <!-- button to submit inputs -->
                <button class="loginBT btn btn-primary mt-3">Sign Up</button>
            </form>
            <br>
            <a href="admin_login.jsp" class="btn btn-primary backBT">Back to Login page</a>
        </div>
    </div>

    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>