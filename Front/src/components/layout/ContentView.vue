<template>
  <section id="intro" class="d-flex align-items-center" style="z-index: 5">
    <b-container>
      <b-row>
        <b-col
          class="col-lg-6 d-flex flex-column justify-content-center pt-4 pt-lg-0 order-2 order-lg-1"
          data-aos="fade-up"
          data-aos-delay="200"
        >
          <h1>
            HappyHouse
            <h3 style="display: inline">with ssafy</h3>
          </h1>
          <h2>여러가지의 조건을 참고해</h2>
          <h2>최적의 조건에 맞는 매물을 찾을 수 있습니다!</h2>
        </b-col>
        <b-col
          class="col-lg-6 order-1 order-lg-2 hero-img"
          data-aos="zoom-in"
          data-aos-delay="200"
        >
          <img src="@/assets/sofa.jpg" class="img-fluid animated" alt="intro" />
        </b-col>
      </b-row>
    </b-container>
  </section>
  <!-- End Intro -->
</template>

<script></script>

<style>
#intro {
  width: 100%;
  height: 70vh;
  background: #ffcc99;
}
#intro .container {
  padding-top: 72px;
}
#intro h1,
h4 {
  margin: 0 0 1em 0;
  font-size: 48px;
  font-weight: 700;
  line-height: 56px;
  color: black;
}
#intro h2 {
  color: black;
  margin: 0.5em 0;
  font-size: 24px;
}
#intro .animated {
  animation: up-down 2s ease-in-out infinite alternate-reverse both;
}
@media (max-width: 991px) {
  #intro {
    height: 100vh;
    text-align: center;
  }
  #intro .animated {
    -webkit-animation: none;
    animation: none;
  }
  #intro .hero-img {
    text-align: center;
  }
  #intro .hero-img img {
    width: 100%;
  }
}
@media (max-width: 768px) {
  #intro h1 {
    font-size: 28px;
    line-height: 36px;
  }
  #intro h2 {
    font-size: 18px;
    line-height: 24px;
    margin-bottom: 30px;
  }
  #intro .intro-img img {
    width: 100%;
  }
}
@media (max-width: 575px) {
  #intro .intro-img img {
    width: 100%;
  }
}
@-webkit-keyframes up-down {
  0% {
    transform: translateY(10px);
  }
  100% {
    transform: translateY(-10px);
  }
}
@keyframes up-down {
  0% {
    transform: translateY(10px);
  }
  100% {
    transform: translateY(-10px);
  }
}
</style>
