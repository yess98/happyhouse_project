<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-green">
      <b-icon icon="newspaper"></b-icon> 최신 부동산 News
    </h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-table-simple hover responsive style="background-color: khaki">
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>뉴스</b-th>
              <b-th></b-th>
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
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import NewsListItem from "@/components/board/item/NewsListItem";

export default {
  name: "InstagramView",
  components: {
    NewsListItem,
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
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(56, 233, 40, 0.3) 30%
  );
}
</style>
