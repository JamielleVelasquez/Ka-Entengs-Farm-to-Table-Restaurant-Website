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
        <!-- jquery for bootstrap datepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- bootstrap datepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" integrity="sha512-TQQ3J4WkE/rwojNFo6OJdyu6G8Xe9z8rMrlF9y7xpFbQfW5g8aSWcygCQ4vqRiJqFsDsE1T6MoAOMJkFXlrI9A==" crossorigin="anonymous"
              referrerpolicy="no-referrer" />

        <title>Review</title>
    </head>

    <body>
    <header-component></header-component>
    <div class="col-10 col-sm-6 align-items-start py-5 mx-auto" style="height: 90vh">
        <div class="display-4 reserveLabel pb-4">Leave a Review</div>
        <p>Let us know how your experience with us went!</p>

        <form action="add.do" method="POST" autocomplete="off">

            <input class="form-control my-2" type="text" name="regName" id="reviewName" placeholder="*Name:" required>
            <div id="datepicker" class="input-daterange my-2">
                <input type="text" class="form-control text-start" name="resDate" placeholder="*Reservation Date" required>
            </div>
            <textarea class="form-control my-2" name="regComment" id="reviewText" placeholder="*How did we do?" required style="height: 20vh"></textarea><br>
            <p class="text-muted">*required</p>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>


    </div>
    <footer-component></footer-component>
    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>
<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: "dd-mm-yy",
            showOtherMonths: true,
            selectOtherMonths: true,
            autoclose: true,
            changeMonth: true,
            changeYear: true,
            orientation: "bottom left"
        });
    });
    $(function () {
        $("#datechecker").datepicker({
            dateFormat: "dd-mm-yy",
            showOtherMonths: true,
            selectOtherMonths: true,
            autoclose: true,
            changeMonth: true,
            changeYear: true,
            orientation: "top"
        });
    });
</script>