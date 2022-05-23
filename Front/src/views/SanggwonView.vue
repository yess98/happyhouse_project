<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3>
    <b-row>
      <b-col>
        <sanggwon-search-bar></sanggwon-search-bar>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import SanggwonSearchBar from "@/components/sanggwon/SanggwonSearchBar.vue";

export default {
  name: "SanggwonView",
  components: {
    SanggwonSearchBar,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
