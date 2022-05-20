<template>
  <div id="app" class="background">
    <navi-bar />
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
.background {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("./assets/city.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}

a.router-link-exact-active {
  color: #3aa4ca;
}
</style>
