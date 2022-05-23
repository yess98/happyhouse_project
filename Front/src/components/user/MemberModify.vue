<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원정보수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form-group label="아이디:" label-for="userid">
            <b-form-input
              id="userid"
              disabled
              v-model="userInfo.userid"
              type="text"
              required
              placeholder="작성자 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group label="비밀번호:" label-for="userpwd">
            <b-form-input
              type="password"
              id="userpwd"
              v-model="user.userpwd"
              required
              placeholder="비밀번호 입력...."
            ></b-form-input>
          </b-form-group>
          <b-form-group label="이름:" label-for="username">
            <b-form-input
              id="username"
              v-model="user.username"
              required
              placeholder="이름 입력...."
            ></b-form-input>
          </b-form-group>
          <b-form-group label="이메일:" label-for="email">
            <b-form-input
              id="email"
              v-model="user.email"
              required
              placeholder="이메일 입력...."
            ></b-form-input>
          </b-form-group>
          <b-button type="button" variant="primary" class="m-1" @click="regist"
            >수정하기</b-button
          >
          <b-button
            type="button"
            variant="success"
            class="m-1"
            @click="movePage"
            >취소</b-button
          >
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberRegister",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
        joindate: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    regist() {
      http
        .put("/user/usermodify", {
          userid: this.userInfo.userid,
          userpwd: this.user.userpwd,
          username: this.user.username,
          email: this.user.email,
          joindate: this.user.joindate,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            alert(msg);
            this.$router.push({ name: "signIn" });
          }
          console.log(data);
          alert(msg);
          this.movePage();
        })
        .catch((error) => {
          console.log(error);
          alert("등록 실패입니다.");
        })
        .finally(() => {
          console.log(this.userInfo.userid);
          console.log(this.user.userpwd);
          console.log(this.user.username);
        });
    },
    movePage() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style></style>
