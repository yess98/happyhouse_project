<template>
  <div>
    <CommonHeader name="로그인" />
    <div class="container my-5">
      <div class="d-flex flex-column align-items-center">
        <h3>아이디와 비밀번호를 입력해 로그인해주세요.</h3>
        <h3>아직 회원이 아니라면 회원가입을 먼저 해주시기 바랍니다.</h3>
        <form class="row g-1 p-0 p-md-4" style="max-width: 32rem">
          <div class="col-12">
            <div class="mb-2">
              <label class="form-label" for="userId">아이디</label>
              <input
                v-model="user.userid"
                id="userid"
                type="text"
                class="form-control form-control-lg"
                placeholder="아이디를 입력하세요"
              />
            </div>
          </div>
          <div class="col-12">
            <div class="mb-2">
              <div class="form-label" for="userPassword">비밀번호</div>
              <input
                @keyup.enter="confirm"
                v-model="user.userpwd"
                id="userpwd"
                type="password"
                class="form-control form-control-lg"
                placeholder="비밀번호를 입력하세요"
              />
            </div>
          </div>
          <div class="col-12 text-center mt-4">
            <a
              @click="confirm"
              class="w-100 btn btn-lg btn-block btn-primary lift text-uppercase"
              >로그인</a
            >
          </div>
          <div class="col-12 text-center mt-4">
            <img
              @click="KakaoLogin"
              src="@/assets/kakao_login_medium_wide.png"
              style="width: 100%; height: 100%"
            />
          </div>
          <div class="col-12 text-center mt-4">
            <router-link
              :to="{ name: 'signUp' }"
              class="flex-grow-1 btn btn-lg btn-block btn-secondary lift text-uppercase"
              >회원가입</router-link
            >
            &nbsp;&nbsp;
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
import CommonHeader from "@/components/layout/CommonHeader.vue";
import http from "@/util/http-common.js";

export default {
  name: "LoginView",
  data() {
    return {
      user: {
        userid: "",
        userpwd: "",
      },
      kakaouser: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
    };
  },
  components: {
    CommonHeader,
  },
  computed: {
    ...mapState("memberStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions("memberStore", ["userConfirm", "getUserInfo"]),

    async confirm() {
      console.log(this.user);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log(this.isLogin);
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
