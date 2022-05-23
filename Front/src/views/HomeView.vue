<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="house"></b-icon> HappyHouse
    </h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
          class="jumbo"
        >
          <template #lead
            >세상 모든 부동산 정보를 집에서 편하게 알아보세요</template
          >

          <hr class="my-4" />
          <b-row>
            <b-col>
              <area-name></area-name>
            </b-col>
            <b-col style="float: right">
              <b-table-simple
                hover
                responsive
                style="font-size: 15px; float: right; background-color: beige"
              >
                <b-thead head-variant="dark">
                  <b-tr>
                    <b-th colspan="3">최신 부동산 관련 News</b-th>
                  </b-tr>
                </b-thead>
                <tbody>
                  <news-list-item
                    v-for="(article, index) in articles"
                    :key="index"
                    v-bind="article"
                    v-bind:index="index"
                  />
                </tbody>
              </b-table-simple>
            </b-col>
          </b-row>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import AreaName from "../components/user/AreaName.vue";
import http from "@/util/http-common";
import NewsListItem from "@/components/board/item/NewsListItem";
export default {
  name: "HomeView",
  components: { AreaName, NewsListItem },
  props: {
    msg: String,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
    http
      .get("http://localhost:9999/vue/news/")
      .then(({ data }) => {
        this.articles = data;
        console.log(this.articles);
      })
      .catch((error) => {
        console.log(error);
        alert("뉴스데이터를 받아오는데 실패했습니다.");
      });
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
#lead {
  font-size: 70px;
  font-weight: bold;
}
</style>
