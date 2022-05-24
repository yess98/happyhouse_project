<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <!-- <b-alert variant="secondary" show><h3>로그인</h3></b-alert> -->
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card
            class="text-center mt-3"
            style="max-width: 40rem; background-color: khaki"
            align="left"
          >
            <b-form class="text-left">
              <b-alert show variant="danger" v-if="isLoginError"
                >아이디 또는 비밀번호를 확인하세요.</b-alert
              >
              <b-form-group label="ID:" label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  required
                  placeholder="ID 입력"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="PASSWORD:" label-for="userpwd">
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="user.userpwd"
                  required
                  placeholder="PASSWORD 입력"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="confirm"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1"
                @click="movePage"
                >회원가입</b-button
              >
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
    <div class="d-flex justify-content-center my-4">
      <img
        @click="KakaoLogin"
        class="btn"
        src="@/assets/kakao_login_medium_wide.png"
      />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/util/http-common.js";
const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
      kakaouser: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "MainView" });
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
    KakaoLogin() {
      console.log(window.Kakao);
      window.Kakao.Auth.login({
        scope: "account_email, gender, profile_nickname",
        success: this.GetMe,
        fail: function (error) {
          console.log(error);
        },
      });
    },
    GetMe(authObj) {
      console.log(authObj);
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          const kakao_account = res.kakao_account;
          console.log(kakao_account);
          alert("로그인 성공 !!");
          this.login(kakao_account);
        },
      });
    },
    async login(kakao_account) {
      const loginuser = {
        userid: kakao_account.email,
        userpwd: "kakao",
        username: kakao_account.profile.nickname,
        email: kakao_account.email,
      };
      http
        .post("/user/register", loginuser)
        .then(({ data }) => {
          console.log(data);
          this.confirmKakao(loginuser);
        })
        .catch(({ error }) => {
          console.log(error);
          this.confirmKakao(loginuser);
        });
    },
    async confirmKakao(loginuser) {
      await this.userConfirm(loginuser);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "MainView" });
      }
    },
  },
};
</script>

<style></style>
