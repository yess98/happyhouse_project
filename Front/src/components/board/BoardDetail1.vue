<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h4 class="mb-3">{{ title }}</h4>
            <hr />
            <div style="min-height: 500px" v-html="content"></div>
          </div>
        </div>
        <router-link
          class="btn btn-sm btn-primary mt-2"
          :to="{ name: 'boardList' }"
          >목록</router-link
        >
        <button
          class="btn btn-sm btn-danger float-end ms-2 mt-2"
          @click="deleteNotice(noticeId)"
        >
          삭제
        </button>
        <router-link
          class="btn btn-sm btn-primary float-end mt-2"
          to="{ name: 'boardModify' }"
        >
          수정
        </router-link>
      </div>
      <div class="col-md-4">
        <ul class="list-group">
          <li
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            글번호 :
            <span>{{ noticeId }}</span>
          </li>
          <li
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            작성자 :
            <span>{{ userName }}</span>
          </li>
          <li
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            조회수 :
            <span>{{ readCount }}</span>
          </li>
          <li
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            작성일시 :
            <span>{{ regDt }}</span>
          </li>
        </ul>
      </div>
    </div>
    <div class="footer"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common.js";

const storeName = "boardStore";
export default {
  name: "BoardNoticeDetail",
  computed: {
    ...mapState(storeName, [
      "readCount",
      "noticeId",
      "title",
      "content",
      "userName",
      "regDt",
    ]),
  },
  methods: {
    deleteNotice(noticeId) {
      this.$swal({
        title: "삭제하시겠습니까?",
        icon: "warning",
        dangerMode: true,
        buttons: true,
      }).then((value) => {
        if (value) {
          http
            .delete("/board/" + noticeId)
            .then(({ data }) => {
              console.log("DeleteVue: data :");
              console.log(data);
              // 현재 route를 /list로 변경.
              this.$router.push({ name: "boardList" });
            })
            .catch((error) => {
              console.log(error);
              this.$swal("서버에 문제가 발생하였습니다.", { icon: "error" });
            });
        }
      });
    },
  },
};
</script>

<style>
* {
  font-size: 18px;
}
</style>
