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
    <%
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        %>
        <header-component></header-component>
        <div class="loginBox">
            <h2 class="loginLabel">Login</h2>
            <form action="login.do" method="POST">
                <!-- input field for username -->
                <div class="userEmail">
                    <input type="text" class="uNameEmail" name="resUserEmail" placeholder="Username / Email">
                </div>
                <br><br>
                <input type="text" class="userPass" name="resPass" placeholder="Password">
                <br><br>
                <!-- button to submit inputs -->
                <button class="loginBT">Login</button>
            </form>
            <button onclick="location.href = 'admin_forgot_pass.jsp'" class="forgetPassBT">forgot password?</button>
        </div>
        <footer-component></footer-component>
        <script src="script.js" type="text/javascript" defer></script>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>