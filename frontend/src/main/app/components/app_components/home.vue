<template>
  <div>
    <!-- Header with image -->
    <div class="main-img container-fluid">
      <div class="fs-3 opacity-25 d-flex justify-content-center text-secondary padding-top">You can find everything
      </div>
    </div>

    <hr>

    <!-- Images-->
    <div class="container-fluid">
      <div class="row g-2">
        <div class="col-6">
          <router-link to="/shop">
            <div class="spec-container border bg-light bg-img-electronics"></div>
          </router-link>
        </div>
        <div class="col-6">
          <router-link to="/shop">
            <div class="spec-container border bg-light bg-img-clothes"></div>
          </router-link>
        </div>

        <div class="col-6">
          <router-link to="/shop">
            <div class="spec-container border bg-light bg-img-food"></div>
          </router-link>
        </div>
        <div class="col-6">
          <router-link to="/shop">
            <div class="spec-container border bg-light bg-img-sport"></div>
          </router-link>
        </div>

        <div class="col-6">
          <router-link to="/shop">
            <div class="spec-container border bg-light bg-img-toys"></div>
          </router-link>
        </div>
        <div class="col-6">
          <router-link to="/shop">
            <div class="spec-container border bg-light bg-img-books"></div>
          </router-link>
        </div>
      </div>
    </div>

    <hr>

    <!-- footer -->
    <footer class="container-fluid margin-bottom">
      <div class="container-md">
        <div class="row">

          <div class="col-4">
            <div class="row">
              <div class="small d-flex justify-content-center"><strong>Contact us</strong></div>
              <div class="col">
                <a href="https://www.facebook.com/">
                  <i aria-hidden="true" class="fa fa-facebook fa-2x p-3" style="color:black"></i>
                </a>
              </div>
              <div class="col">
                <a href="https://www.youtube.com/">
                  <i aria-hidden="true" class="fa fa-youtube fa-2x p-3" style="color:black"></i>
                </a>
              </div>
              <div class="col">
                <a href="https://www.instagram.com/">
                  <i aria-hidden="true" class="fa fa-instagram fa-2x p-3" style="color:black"></i>
                </a>
              </div>
              <div class="col">
                <a href="https://www.twitter.com/">
                  <i aria-hidden="true" class="fa fa-twitter fa-2x p-3" style="color:black"></i>
                </a>
              </div>
            </div>
          </div>

          <div class="col-8">
            <div class="row">
              <div class="col d-flex justify-content-center">
                <ul>
                  <li class="small"><strong>Something</strong></li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                </ul>
              </div>
              <div class="col d-flex justify-content-center">
                <ul>
                  <li class="small"><strong>Something</strong></li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                </ul>
              </div>
              <div class="col d-flex justify-content-center">
                <ul>
                  <li class="small"><strong>Something</strong></li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                </ul>
              </div>
              <div class="col d-flex justify-content-center">
                <ul>
                  <li class="small"><strong>Something</strong></li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                  <li class="small">link</li>
                </ul>
              </div>
            </div>
          </div>

        </div>
      </div>
    </footer>
  </div>
</template>

<style scoped>
@import "bootstrap/dist/css/bootstrap.min.css";
@import "font-awesome";

body, html {
  height: 100%
}

body, h1, h2, h3, h4, h5, h6 {
  font-family: sans-serif
}

.main-img {
  height: 700px;
  background-repeat: no-repeat;
  background-size: cover;
  background-image: url("../pictures/main.jpg");
  min-height: 100%;
}

.bg-img-electronics {
  background-image: url("../pictures/electronics.jpg");
  background-size: cover;
}

.bg-img-clothes {
  background-image: url("../pictures/clothes.jpg");
  background-size: cover;
}

.bg-img-sport {
  background-image: url("../pictures/sport.jpg");
  background-size: cover;
}

.bg-img-food {
  background-image: url("../pictures/food.jpg");
  background-size: cover;
}

.bg-img-books {
  background-image: url("../pictures/books.jpg");
  background-size: cover;
}

.bg-img-toys {
  background-image: url("../pictures/toys.jpg");
  background-size: cover;
}

.spec-container {
  width: 100%;
  height: 0;
  padding-bottom: 60%;
}

.padding-top {
  padding-top: 7%;
}

.margin-bottom {
  margin-bottom: 5%;
}

li {
  list-style-type: none;
  padding: .1em;
}

footer {
  padding-top: 5%;
}
</style>