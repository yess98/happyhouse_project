<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
          class="jumbo"
        >
          <template #header>Happy House</template>

          <template #lead>
            세상의 모든 부동산 정보를 집에서 편하게 알아보세요
          </template>

          <hr class="my-4" />
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0.5) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.jumbo {
  font-size: 50px;
  font-weight: bold;
  background-color: #ffffff;
  background-color: rgba(255, 255, 255, 0.5);
}
#header {
  font-size: 50px;
  font-weight: bold;
}
#lead {
  font-size: 50px;
  font-weight: bold;
}
</style>
