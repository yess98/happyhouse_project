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
  color: #444444;
}
a {
  color: black;
}
a:hover {
  color: black;
  text-decoration: none;
}
* {
  text-decoration: none;
}
</style>
