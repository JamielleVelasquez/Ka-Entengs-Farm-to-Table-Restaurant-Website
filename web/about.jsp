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

        <title>About Us</title>
    </head>

    <body>
    <header-component></header-component>
    <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
        <%
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
            session = null;
        %>
    <div class="container-fluid p-0 position-relative" style="background-image: url('img/about-banner.jpg');
         height: 70vh;
         background-size: cover;">
    </div>
    <div id="iternerary" class="container-fluid position-relative p-0" style="margin-top: 6rem;">
        <div id="tour-info-headline" class="container col-12 bg-ivory p-3 py-5 p-lg-5 start-50 translate-middle" style="position: absolute; top: -9rem;">
            <h1 class="text-center fw-bolder">Enteng's Farm</h1>
            <hr>
            <p>The farm was inspired by the nostalgia of our hometowns in Dulag and Tacloban, Leyte, and as we found our way to Manila for opportunities, Pulilan, Bulacan is a beautiful intersection of both. It is an oasis of one's longing for the "probinsyano"
                in us to partake in missed homegrown delicacies and favorites while in the great company of friends, family, and like-minded people. We aim to go back to the source of abundance in the Philippines and that is our land, people, and community.
            </p>
        </div>
        <div id="container-text-image" class="container-fluid g-0 row p-3 py-5  p-sm-5 align-items-center" style="height: 550px;">
            <div class="col-12 col-sm-6 col-lg-8 p-0 p-sm-4">
                <h1 class="mb-4">Why Natural Farming?</h1>
                <p>Natural Farming (Traditional Farming) was the original type of agriculture, and has been practiced for thousands of years. <br><br> All traditional farming is now considered to be "Natural Farming" although at the time there were no known
                    inorganic methods. <br><br> Health and environment considerations are also some of the big factors why some of our farmers are now shifting to Natural Farming Management.</p>
            </div>
            <div id="container-image" class="col-12 col-sm-6 col-lg-4" style="background-image: url('img/about-1.jpg');
                 background-position: center;
                 height: 90%;
                 background-size: cover;
                 float: right;"></div>
        </div>
        <div class="container-fluid row align-items-center text-center p-3 py-5  p-sm-5 text-white g-0 bg-green">
            <div class="col order-1 order-md-0">
                <h4 class="p-2">No pesticides</h4>
                <h4 class="p-2">No herbicides</h4>
                <h4 class="p-2">No tillage</h4>
            </div>
            <div class="col-12 col-md-4 order-0 order-md-1">
                <p class="fw-lighter">
                    Natural Farming methods have been growing over the years and have shown evidence of increased food production by 40%, environment conservation and sustainable livelihoods.
                    <br><br> It makes all inputs from natural materials, indigenous mircroorganism, does not use insecticides and herbicides, traditional agricultural practices like not tilling the land, zero emission of waste through recycling.
                </p>
            </div>
            <div class="col order-2 order-md-2">
                <h4 class="p-2">No chemical fertilizer</h4>
                <h4 class="p-2">No pollution</h4>
                <h4 class="p-2">No artificial heating</h4>
            </div>
        </div>
        <div id="container-text-image" class="container-fluid g-0 row p-3 p-sm-5 align-items-center" style="height: 550px;">
            <div id="container-image" class="col-12 col-sm-6 col-lg-4" style="background-image: url('img/about-2.jpg');
                 background-position: center;
                 height: 90%;
                 background-size: cover;
                 float: left;"></div>
            <div class="col-12 col-sm-6 col-lg-8 p-0 p-sm-4 ps-sm-5">
                <h1 class="mb-4 mt-5">Integrated and Sustainable</h1>
                <p>Natural Farming uses science and technology to make use of all resources available in the farm. It also promotes the zero-waste cycle wherein the wastages in each project is used and recycle for the other projects, making them interdependent
                    to each other and considering them as one big project.
                    <br><br> The farm does not focus only on one crop or one project, the integration of different projects like aquaculture, livestocks and multi-cropping makes it more sustainable and profitable.</p>
            </div>

        </div>
    </div>
    <div class="container-fluid row text-center g-0 bg-ivory lh-lg p-3 p-sm-5">
        <h1 class="fw-bold pt-5">
            Our Projects
        </h1>
        <hr class="w-75 mx-auto mb-5 mt-4 opacity-100 color-green" style="height: 3px;">
        <div class="col-12 col-lg-4 pb-5">
            <ul class="list-unstyled fs-5 fw-lighter">
                <li>Organic Fertilizers
                </li>
                <li>Liquid Fertilizers
                </li>
                <li>Vermiculture
                </li>
                <li>Lowland Strawberries
                </li>
            </ul>
        </div>
        <div class="col-12 col-lg-4 pb-5">
            <ul class="list-unstyled fs-5 fw-lighter">
                <li>Vegetable Seedlings Production
                </li>
                <li>Vegetables Production
                </li>
                <li>Herbs Production
                </li>
                <li>Mushroom Production
                </li>
                <li>Free-range Hubbard Chickens
                </li>
            </ul>
        </div>
        <div class="col-12 col-lg-4 pb-5">
            <ul class="list-unstyled fs-5 fw-lighter">
                <li>Native Pigs Raising
                </li>
                <li>Aquaponics
                </li>
                <li>Fruits and Vegetables Processing
                </li>
                <li>Poultry Meat & Pork Meat Processing</li>
            </ul>
        </div>

    </div>
    <footer-component></footer-component>
    <script src="script.js" type="text/javascript" defer></script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>