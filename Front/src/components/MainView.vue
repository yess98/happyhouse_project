<template>
  <div style="position: relative; height: 100vh; overflow: auto">
    <!-- <navi-bar /> -->
    <content-view />
    <footer-view />
  </div>
</template>

<script>
// import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import ContentView from "@/components/layout/ContentView.vue";
import FooterView from "@/components/layout/FooterView.vue";
export default {
  name: "MainView",
  components: {
    // NaviBar,
    ContentView,
    FooterView,
  },
};
</script>

<style>
body {
  font-family: "Jua", sans-serif;
  color: #444444;
}
a {
  color: #47b2e4;
}
a:hover {
  color: #73c5eb;
  text-decoration: none;
}
h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: "Jua", sans-serif;
}
</style>
