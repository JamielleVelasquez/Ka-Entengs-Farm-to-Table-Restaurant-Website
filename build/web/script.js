// Reusable Components
class Header extends HTMLElement {
    constructor() {
        super();
    }


    connectedCallback() {
        this.innerHTML = `
        <header class="sticky-top bg-white" id="header">
        <nav class="navbar navbar-expand-md p-0">
            <div class="container-fluid">
                <a class="navbar-brand h4 text-dark px-0 px-sm-4 d-none d-sm-block" href="landing_page.html">Enteng's Farm to Table Restaurant</a>
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
                        <li class="nav-item px-2 my-1"><a href="tour_info.html" class="btn btn-primary nav-link">Reserve a Tour</a>
                        </li>
                    </ul>
                </div>
            </div>


        </nav>
        </header>
    `;
    }
}

class Footer extends HTMLElement {
    constructor() {
        super();
    }


    connectedCallback() {
        this.innerHTML = `
        <footer>
        <div class="container-fluid p-5 text-center text-md-start" id="footer">
            <div class="row">
                <div class="col-12 g-3 col-md-6">
                    <h3> Enteng's Farm to Table Restaurant</h3>
                    <p>Enteng's Farm practices sustainable and organic farming. We take pride in using natural resources and natural methods to produce quality output.</p>
                </div>
                <div class="col-12 g-3 col-md-2">
                    <h3>Our Socials</h3>
                    <p>Placeholder</p>
                </div>
                <div class="col-12 g-3 col-md-2">
                    <h3>About Us</h3>
                    <p>Placeholder</p>
                </div>
                <div class="col-12 g-3 col-md-2">
                    <h3>Contact Us</h3>
                    <p>Placeholder</p>
                </div>
            </div>
            <hr>
            <p class="text-center">© 2021 Enteng's Farm to Table Restaurant. All rights reserved</p>
        </div>
        </footer>
    `;
    }
}

customElements.define('header-component', Header)
customElements.define('footer-component', Footer)

$(document).ready(function() {

    $('.input-daterange').datepicker({
        format: 'dd-mm-yyyy',
        autoclose: true,
        widgetPositioning: {
            horizontal: "bottom",
            vertical: "auto"
        }
    });

});