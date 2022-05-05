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

        <title>Reservation</title>
    </head>

    <body>
    <header-component></header-component>
    <div class="container-fluid p-4 p-lg-5">
        <div class="display-4 reserveLabel">Reservation</div>
        <div class="row justify-content-center">
            <div class="col-12 col-sm-5 p-1 ms-1 mb-4 p-lg-5 ms-lg-5">
                <form action="reserve.do" method="POST" autocomplete="off">
                    <div class="row g-0 m-0 my-4">
                        <!-- input field for amount of people -->
                        <input type="number" class="form-control w-50" name="resNumPpl" placeholder="*Number of people" required min="1" max="30">
                        <!-- input field for date -->
                        <div id="datepicker" class="input-daterange m-0 w-50">
                            <input type="text" class="form-control" name="resDate" placeholder="*Date" required>
                        </div>
                    </div>

                    <!-- input field for username -->
                    <input type="text" class="flName form-control p-2 m-0 my-4" name="resFn" placeholder="*First Name" required>
                    <input type="text" class="flName form-control p-2 m-0 my-4" name="resLn" placeholder="*Last Name" required>
                    <input type="number" class="userpass form-control p-2 m-0 my-4" name="resNum" placeholder="*Phone Number" required>
                    <input type="email" class="userpass form-control p-2 m-0 my-4" name="resEmail" placeholder="*Email Address" required>
                    <div class="form-check my-4">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                        <label class="form-check-label" for="invalidCheck">
                            Agree to <a href="terms_and_conditions.html" target="_blank">terms and conditions</a>
                        </label>
                    </div>
                    <p class="text-muted">*required</p>
                    <!-- error messages go here -->
                    <p class="text-danger">${errorMessage}</p>
                    <!-- button to submit inputs -->
                    <button class="reserveBT btn btn-primary">Reserve</button>
                </form>
                <form action="checkSlots.do" class="row justify-content-center p-3 pt-5" autocomplete="off">
                    <!-- variable to display available slots -->
                    <p class="w-auto text-center my-auto">${selectedDateSlots} slots left on </p>
                    <!-- input field for date -->
                    <div id="datechecker"class="input-daterange m-0" style="width: 135px">
                        <input type="text" class="form-control" name="resDate" placeholder="Date" value="${selectedDate}" required>
                    </div>
                    <button class="btn btn-primary w-auto">Check Availability</button>
                </form>
            </div>
            <div class="vr line col-2 mx-4 d-none d-sm-block"></div>
            <div class="col-12 col-sm-5 p-1 ms-1 p-lg-5 ms-lg-5">
                <h2>Downpayment</h2>
                <p>
                    We require a 50% non-refundable down-payment upon reservation.
                </p>
                <h2>Cancellation Policy</h2>
                <!-- Needs to be Checked -->
                <p>
                    Tours can always be rescheduled, provided there is still space available for your group. To
                    reschedule, or cancel your tour, please contact us at least 3 business days prior to your appointment by email at entengsfarm@gmail.com or by phone +63 977 8124594.
                </p>
                <p>
                    Bookings, rescheduled or cancelled, less than 3 business days prior to your tour date, will be charged a Php 1250.00 administration fee. There is a Php 5000.00 no-show fee, per tour group of 15-30 people (not per school), for groups that simply don't
                    show up on the day of their tour without cancelling in advance.
                </p>

            </div>
        </div>

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