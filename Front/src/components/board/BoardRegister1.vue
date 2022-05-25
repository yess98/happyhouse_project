<template>
  <div class="container my-5">
    <form class="row g-3 basic-form" @submit="onSubmit">
      <div class="col-md-12">
        <label class="form-label ms-3"
          >제목 <sup class="text-danger">*</sup></label
        >
        <input
          v-model="article.title"
          type="text"
          class="form-control"
          placeholder="제목을 입력하세요"
          required
        />
      </div>
      <div class="col-12">
        <div class="my-3">
          <label class="form-label ms-3"
            >내용 <sup class="text-danger">*</sup></label
          >
          <ckeditor id="ck" v-model="CKEditor"></ckeditor>
        </div>
      </div>
      <div class="col-12">
        <button type="submit" class="btn btn-primary float-end">
          작성완료
        </button>
        <router-link
          :to="{ name: 'boardList' }"
          class="me-2 btn btn-secondary float-end"
          >취소</router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import Vue from "vue";
import CKEDITOR from "ckeditor4-vue";

Vue.use(CKEDITOR);
import { writeArticle } from "@/api/board";
import { mapState } from "vuex";
export default {
  name: "boardRegister",
  data() {
    return {
      CKEditor: "",
      article: {
        userid: "",
        title: "",
        content: "",
      },
    };
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    this.article.userid = this.userInfo.userid;
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      console.log(this.CKEditor);
      this.boardInsert();
    },
    boardInsert() {
      writeArticle(
        {
          userid: this.article.userid,
          subject: this.article.title,
          content: this.CKEditor,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>
<style>
.ck-content {
  height: 500px;
}
* {
  font-size: 18px;
}
</style>
