// Reusable Components
class Header extends HTMLElement {
    constructor() {
        super();
    }


    connectedCallback() {
        this.innerHTML = `
        <header id="header">
        <nav class="navbar justify-content-md-between">
            <a class="navbar-brand h4 text-dark px-0 px-sm-4" href="landing_page.html">Enteng's Farm to Table Restaurant</a>
            <ul class="list-group list-group-horizontal list-group-flush align-items-center">
                <li class="list-group-item"><a href="about.html">About Us</a>
                </li>
                <li class="list-group-item"><a href="menu.html">Menu</a>
                </li>
                <li class="list-group-item"><a href="gallery.html">Gallery</a>
                </li>
                <li class="list-group-item"><a href="tour_info.html" class="btn btn-primary">Reserve</a>
                </li>
            </ul>
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
        <div class="container-fluid p-5 text-center text-sm-start" id="footer">
            <div class="row">
                <div class="col-12 col-sm-6">
                    <h3> Enteng's Farm to Table Restaurant</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In efficitur aliquam arcu, vitae lobortis sapien pulvinar at.</p>
                </div>
                <div class="col-12 col-sm-2">
                    <h3>Our Socials</h3>
                    <p>Placeholder</p>
                </div>
                <div class="col-12 col-sm-2">
                    <h3>About Us</h3>
                    <p>Placeholder</p>
                </div>
                <div class="col-12 col-sm-2">
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