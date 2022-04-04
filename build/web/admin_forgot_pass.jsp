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

    <title>Forgot Password</title>
</head>

<body>
    <header-component></header-component>
    <div class="row align-items-center g-0" style="height: 80vh;">
        <div class="forgotPassBox col-12 p-3 col-sm-9 col-lg-6 mx-auto text-center">
            <h2 class="forgotLabel display-3">Forgot Password?</h2>
            <hr class="w-25 mx-auto mb-5 color-green opacity-100" style="height: 2px;">
            <form action="forgotPass.do" method="POST">
                <!-- input field for email -->
                <div class="forgotEmail">
                    <input type="text" class="fEmail form-control mx-auto m-4" name="adminEmail" placeholder="Email" required>
                </div>
                <!-- button to submit inputs -->
                <button class="sendEmailBT btn btn-primary mt-3">Send Email</button>
            </form>
        </div>
    </div>

    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>