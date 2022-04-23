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

        <title>Ka Enteng's Farm and Table Restaurant</title>
    </head>

    <body>
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
    <header-component></header-component>
    
    <div class="container-fluid p-0 position-relative" style="background-image: url('img/landing-page-banner.png');
         height: 90vh;
         background-size: cover;">
        <div id="landing-page-main-header" class="text-white display-3 p-2 ps-sm-5 pb-sm-4 fw-bold">Experience Nature Through Us</div>
    </div>
    <div id="container-text-image" class="container-fluid g-0 row p-3 p-sm-5 align-items-center" style="height: 550px;">
        <div class="display-3 text-center col-12 fw-bold ">Sa Kabukiran</div>
        <hr class="mx-auto col-12 mb-3 mb-sm-5">
        <div class="col-12 col-sm-6 col-lg-8 p-4">
            <h1 class="mb-4">Farm Tour and Workshops</h1>
            <p>Take a guided tour around the farm and explore what we have to offer. Participate in workshops that teaches organic farming techniques and farm-to-table meals that you can apply in your own home. Learn all about the life cycle of plants, pollinators,
                and microoganisms as well as their benefits to your health and environment. See the cycle of food for youself as you eat healthy and nutritious meals that were freshly picked from the farm.</p>
            <a href="tour_info.jsp">
                <button class="btn btn-primary">
                    View Details
                </button>
            </a>
        </div>
        <div id="container-image" class="col-12 col-sm-6 col-lg-4" style="background-image: url('img/landing-page-tour.png');
             background-position: center;
             height: 90%;
             background-size: cover;
             float: right;"></div>
    </div>
    <div class="container-fluid p-1 py-4 p-sm-5 bg-green">
        <div class="row justify-content-center align-items-center mt-4 g-0">
            <h1 class="col-12 col-sm-2 text-center text-white m-0 p-sm-0">Our Mission</h1>
            <div class="vr col-1 p-0 mx-5 text-white opacity-100 d-none d-sm-block"></div>
            <hr class="col-4 p-0 m-0 mt-2 text-white opacity-100 d-block d-sm-none">
            <p class="col-12 col-sm-6 text-white m-0 p-3 p-sm-3 px-sm-0">To pioneer and demonstrate a fully integrated and sustainable living environment, using appropriate technologies both traditional and modern, and to share our knowledge with others for the benefit of the present & future generations.</p>
        </div>
    </div>
    <div class="container-fluid p-0 bg-ivory">

        <div class="row g-0">
            <div class="img-fluid col-12 col-lg-4 p-0 order-last order-lg-first" style="background-image: url('img/landing-page-resto.jpg');
                 background-position: center;
                 height: 500px;
                 background-size: cover;">
            </div>
            <div class="col-12 col-lg-8 p-3 py-5 p-sm-5 my-auto">
                <h1 class="mb-4">Bringing homegrown flavor to your platter</h1>
                <p class="mb-4">Our restaurant situated at the heart of Enteng's farm provides the perfect place to enjoy farm-to-table meals. We are serving homegrown food made with the freshest ingredients straight from the farm.
                </p>
                <a href="menu.jsp">
                    <button class="btn btn-primary">
                        Our Menu
                    </button>
                </a>
            </div>
        </div>
    </div>
    <div class="container-fluid p-5">

        <div class="row justify-content-center">
            <div class="display-3 col-12 col-md-3 mb-4 m-md-0">Reviews</div>
            <div class="vr p-0 mx-5 opacity-50 d-none d-md-block"></div>
            <div class="col-12 col-md-7 overflow-auto p-0" style="height: 50vh;">
                <jsp:include page="/PrintReview.do"/>
            </div>
        </div>
    </div>
    <div class="container-fluid p-5 bg-green">

        <div class="row align-items-center">
            <form class="col-12 col-sm-7 p-0 pe-sm-5" action="add.do" method="POST" autocomplete="off">

                <input class="form-control" type="text" name="regName" id="reviewName" placeholder="(Optional) Name:"><br>
                <textarea class="form-control" name="regComment" id="reviewText" placeholder="How did we do?" required></textarea><br>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <div class="vr p-0 text-white opacity-50 d-none d-sm-block"></div>
            <div class="col-12 col-sm-4 text-center mx-auto order-first order-sm-last pb-4 p-sm-0">
                <h1 class="text-white">Past Customer?</h1>
                <h6 class="text-white">Leave a Review!</h6>
            </div>

        </div>
    </div>
    <footer-component></footer-component>

    <script src="script.js" type="text/javascript" defer></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>