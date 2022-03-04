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

    <title>Error!</title>
</head>

<body>
    <!-- This error page is also for 404 -->
    <header-component></header-component>
    <section class="errorMessageSec row align-items-center g-0 p-2 text-center" style="height: 90vh;">
        <div class="errorMsgDiv">
            <div class="errorBox">
                <h1>Oops! Something went wrong.</h1>
                <!-- Error Type -->
                <p class="fs-5 fw-bold text-danger">Error 404</p>
                <p class="col-9 col-sm-6 mx-auto">

                    <%
                        String errMsg = (String) getServletContext().getAttribute("errorMessage");
                        out.print(errMsg);
                    %>

                </p>
                <button class="btn btn-primary backBT" onclick="goBack()">Go Back</button>
            </div>
        </div>
    </section>
    <footer-component></footer-component>
    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>

</html>