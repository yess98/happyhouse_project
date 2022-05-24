<template>
  <!-- ======= Footer ======= -->
  <footer id="footer" class="mt-5">
    <div class="footer-top">
      <b-container>
        <hr class="my-5" />
        <b-row>
          <b-col class="col-lg-4 col-md-6 footer-contact">
            <h3>양은서</h3>
            <p><strong>Email:</strong> diddmstj980914@gmail.com<br /></p>
            <div class="social-links mt-3">
              <b-row>
                <b-col class="col-lg-2">
                  <a href="https://github.com/yess98" class="github"
                    ><box-icon
                      color="white"
                      type="logo"
                      name="github"
                      animation="tada"
                    ></box-icon
                  ></a>
                </b-col>
                <b-col class="my-auto">Github</b-col>
              </b-row>
            </div>
          </b-col>

          <b-col class="col-lg-4 col-md-6 footer-contact">
            <h3>엄재웅</h3>
            <p><strong>Email:</strong> zzang6535@gmail.com<br /></p>
            <div class="social-links mt-3">
              <b-row>
                <b-col class="col-lg-2">
                  <a href="https://github.com/eomjaewoong" class="github"
                    ><box-icon
                      color="white"
                      type="logo"
                      name="github"
                      animation="tada"
                    ></box-icon
                  ></a>
                </b-col>
                <b-col class="my-auto">Github</b-col>
              </b-row>
            </div>
          </b-col>

          <b-col class="col-lg-4 col-md-6 footer-contact">
            <h3>Developed with</h3>
            <div class="social-links mt-3">
              <b-row>
                <b-col class="col-lg-2">
                  <box-icon type="logo" name="spring-boot" size="md"></box-icon>
                </b-col>
                <b-col class="my-auto">Spring Boot</b-col>
              </b-row>

              <b-row>
                <b-col class="col-lg-2">
                  <box-icon type="logo" name="vuejs" size="md"></box-icon>
                </b-col>
                <b-col class="my-auto">Vue.js</b-col>
              </b-row>

              <b-row>
                <b-col class="col-lg-2">
                  <box-icon name="bootstrap" type="logo" size="md"></box-icon>
                </b-col>
                <b-col class="my-auto">Bootstrap</b-col>
              </b-row>

              <b-row>
                <b-col class="col-lg-2">
                  <box-icon name="map" type="solid" size="md"></box-icon>
                </b-col>
                <b-col class="my-auto">Kakao map</b-col>
              </b-row>
            </div>
          </b-col>
        </b-row>
      </b-container>
    </div>

    <div class="container footer-bottom clearfix">
      <div class="copyright">
        &copy; Copyright <strong><span>양은서 & 배현중</span></strong
        >. All Rights Reserved
      </div>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/arsha-free-bootstrap-html-template-corporate/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer>
  <!-- End Footer -->
</template>

<script>
import "boxicons";
export default {};
</script>

<style>
#footer {
  font-size: 14px;
  background: black;
}
#footer .footer-top {
  padding: 60px 0 30px 0;
  background: #fff;
}
#footer .footer-top .footer-contact {
  margin-bottom: 30px;
}
#footer .footer-top .footer-contact h3 {
  font-size: 28px;
  margin: 0 0 10px 0;
  padding: 2px 0 2px 0;
  line-height: 1;
  text-transform: uppercase;
  font-weight: 600;
  color: black;
}
#footer .footer-top .footer-contact p {
  font-size: 14px;
  line-height: 24px;
  margin-bottom: 0;
  color: black;
}
#footer .footer-top h4 {
  font-size: 16px;
  font-weight: bold;
  color: black;
  position: relative;
  padding-bottom: 12px;
}
#footer .footer-top .footer-links {
  margin-bottom: 30px;
}
#footer .footer-top .footer-links ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
#footer .footer-top .footer-links ul i {
  padding-right: 2px;
  color: black;
  font-size: 18px;
  line-height: 1;
}
#footer .footer-top .footer-links ul li {
  padding: 10px 0;
  display: flex;
  align-items: center;
}
#footer .footer-top .footer-links ul li:first-child {
  padding-top: 0;
}
#footer .footer-top .footer-links ul a {
  color: black;
  transition: 0.3s;
  display: inline-block;
  line-height: 1;
}
#footer .footer-top .footer-links ul a:hover {
  text-decoration: none;
  color: black;
}
#footer .footer-top .social-links a {
  font-size: 18px;
  display: inline-block;
  background: black;
  color: #fff;
  line-height: 1;
  padding: 8px 0;
  margin-right: 4px;
  border-radius: 50%;
  text-align: center;
  width: 36px;
  height: 36px;
  transition: 0.3s;
}
#footer .footer-top .social-links a:hover {
  background: black;
  color: #fff;
  text-decoration: none;
}
#footer .footer-bottom {
  padding-top: 30px;
  padding-bottom: 30px;
  color: #fff;
}
#footer .copyright {
  float: left;
}
#footer .credits {
  float: right;
  font-size: 13px;
}
#footer .credits a {
  transition: 0.3s;
}
@media (max-width: 768px) {
  #footer .footer-bottom {
    padding-top: 20px;
    padding-bottom: 20px;
  }
  #footer .copyright,
  #footer .credits {
    text-align: center;
    float: none;
  }
  #footer .credits {
    padding-top: 4px;
  }
}
</style>
