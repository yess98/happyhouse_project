<template>
  <div id="app">
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
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");

#app {
  font-family: "Jua", sans-serif;
  font-size: 1.5em;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
