<%@page import="controllers.PrintItenerary"%>
<%@page import="java.util.ArrayList"%>
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

        <title>Tours</title>
    </head>

    <body>
        <header class="sticky-top bg-white" id="header">
            <nav class="navbar navbar-expand-md p-0">
                <div class="container-fluid">
                    <a class="navbar-brand h4 text-dark px-0 px-sm-4 d-none d-sm-block" href="landing_page.jsp">Enteng's Farm to Table Restaurant</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-toggle">
                        <i class="bi bi-list"></i>
                    </button>
                    <div class="collapse navbar-collapse flex-grow-0" id="navbar-toggle">
                        <ul class="navbar-nav p-2">
                            <li class="nav-item px-2 my-1"><a class="nav-link" href="about.html">About Us</a>
                            </li>
                            <li class="nav-item px-2 my-1"><a class="nav-link" href="menu.html">Menu</a>
                            </li>
                            <li class="nav-item px-2 my-1"><a class="nav-link" href="gallery.html">Gallery</a>
                            </li>
                            <li class="nav-item px-2 my-1"><a href="reservation.jsp" class="btn btn-primary nav-link">Reserve a Tour</a>
                            </li>
                        </ul>
                    </div>
                </div>


            </nav>
        </header>
        <div class="container-fluid p-0 position-relative" style="background-image: url('https://picsum.photos/1920/720');
             height: 70vh;
             background-size: cover;">
        </div>
        <div id="iternerary" class="container-fluid position-relative p-0 p-sm-5" style="margin-top: 6rem;">
            <div id="tour-info-headline" class="container col-12 bg-ivory  py-4 p-3 p-lg-5 start-50 translate-middle" style="position: absolute; top: -9rem;">
                <h1 class="text-center fw-bold">Get in touch with nature for a day</h1>
                <hr>
                <p>Book a tour of the farm and experience organic farming at work. Learn all about the relationships of plants towards pollinators and microorganisms as we teach you how to utilize them for your own garden. Become a part of the food cycle as
                    you eat farm-to-table meals with freshly picked ingredients. <br> <br> Natural Farming methods have been growing over the years and have shown evidence of increased food production by 40%, environment conservation and sustainable livelihoods.
                    It makes all inputs from natural materials, indigenous mircroorganism, does not use insecticides and herbicides, traditional agricultural practices like not tilling the land, zero emission of waste through recycling.
                </p>
                <a href="#iternerary">
                    <button class="btn btn-primary">Itenerary</button>
                </a>

            </div>
            <div class="container p-3 p-sm-0" style="margin-top: 3rem;">
                <h1 class="text-center">Itenerary</h1>
                <hr class="col-7 mx-auto color-green opacity-100 mb-5" style="height: 2px;">
                <div class="row my-3 justify-content-center">
                    <div class="col-2 col-sm-1">Time</div>
                    <div class="col-10 col-lg-6 col-sm-8">Activity</div>         
                </div>
                <div>
                           <% ArrayList<String> list = new ArrayList<String>();
                           list=PrintItenerary.ITarray();
                        for (int i=0;i<list.size();i++)
          {

              out.println(list.get(i));

          } 
                    %>  
                    </div>
            </div>

            <div class="container-fluid p-3 p-sm-5 position-relative" style="background-image: url('https://picsum.photos/1920/720');
                 height: 400px;
                 background-size: cover;">
                <div id="tour-info-things-to-remember" class="bg-green col-12 col-sm-6 col-md-5 col-lg-4 col-xxl-3 me-auto text-white p-3 p-md-5">
                    <h1>Things to Remember</h1>
                    <p>Bring an extra change of clothes, a tumbler for hydration, a hat, and a fan to stay cool. Wear comfortable clothes and footwear for the farm: long sleeves , ¾ pants / leggings, Crocs, boots, or bare feet for grounding.</p>
                </div>
            </div>
            <div class="container-fluid p-4 p-sm-5 text-center bg-ivory">
                <div class="mx-auto m-0 mt-sm-5">
                    <h1>Prices</h1>
                    <hr class="mb-4 w-25 mx-auto">
                    <p>
                    <p class="fw-bold">Adult: Php 1,500*/person</p>
                    (with Jeron Travel Coaster/Van, Php 2,000/person)
                    <br>(Group packages with minimum of 15 people is Php 1,425/person)
                    <p class="fw-bold"><br>Child 4yrs+: Php 1,200*/person</p>
                    (with Jeron Travel Coaster/Van, Php 1,575*/person)
                    <p class="fw-bold"><br>Child 3yrs and below: FREE</p>
                    +Php 150 for Drivers/Nanny
                    <br>* inclusive of 12% VAT and 10% Service charge
                    </p>
                    <a href="reservation.jsp">
                        <button class="btn btn-primary mt-3">Reserve a Tour</button>
                    </a>
                </div>

            </div>

            <footer-component></footer-component>
            <script src="script.js" type="text/javascript" defer></script>

            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>

</html>