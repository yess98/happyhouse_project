<template>
  <div class="container my-5">
    <div class="card bg-transparent border-0 mt-4">
      <table
        id="dataTable"
        class="myDataTable table align-middle table-bordered mb-0 custom-table nowrap dataTable text-center w-100"
      >
        <thead>
          <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(article, index) in articles" v-bind:key="index">
            <td>
              <span class="badge bg-success">{{ article.articleno }}</span>
            </td>
            <td class="w-50 text-start">
              <h6 class="mb-0">
                <a
                  @click="boardDetail(article.articleno)"
                  class="color-700 text-decoration-none"
                  >{{ article.subject }}</a
                >
              </h6>
            </td>
            <td>
              <div class="dropdown">
                <button class="btn btn-sm text-decoration-none" role="button">
                  {{ article.userid }}
                </button>
              </div>
            </td>
            <td>
              <span v-if="article.regtime" class="d-block">{{
                article.regtime
              }}</span>
            </td>
            <td>
              <span class="d-block">{{ article.hit }}</span>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-if="this.userInfo" class="my-2 d-flex justify-content-end">
        <router-link class="btn btn-primary" :to="{ name: 'boardRegister' }"
          >글쓰기</router-link
        >
      </div>
    </div>
  </div>
</template>
<script>
import { listArticle, getArticle } from "@/api/board.js";
import { mapState, mapMutations } from "vuex";

export default {
  name: "BoardNoticeList",
  data() {
    return {
      articles: [],
    };
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  methods: {
    ...mapMutations("boardStore", ["SET_BOARD_DETAIL"]),
    boardDetail(articleno) {
      let param = articleno;
      console.log(param);
      getArticle(
        param,
        (response) => {
          console.log(response.data);
          this.SET_BOARD_DETAIL(response.data);
          this.$router.push({ name: "boardDetail" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
        console.log(response.data);
      },
      (error) => {
        console.log(error);
      },
    );
  },
};
</script>
