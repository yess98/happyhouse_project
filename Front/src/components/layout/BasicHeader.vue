<template>
  <div class="navbar navbar-expand-lg sticky-top py-md-2 border-bottom">
    <div class="container">
      <div class="left d-flex">
        <button
          class="navbar-toggler color-900"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbar-collapse"
          aria-controls="navbar-collapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <i class="fa fa-bars"></i>
        </button>
        <router-link class="navbar-brand" to="/">
          <img style="width: 64px" src="@/assets/HAPPYLOGO.webp" />
        </router-link>
      </div>
      <div
        class="collapse navbar-collapse justify-content-between"
        id="navbar-collapse"
      >
        <ul class="navbar-nav">
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" :to="{ name: 'apt' }"
              >실거래가</router-link
            >
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" :to="{ name: 'sanggwon' }"
              >상권정보</router-link
            >
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" :to="{ name: 'board' }">
              공지사항</router-link
            >
          </li>
        </ul>
      </div>
      <div class="d-flex align-items-center">
        <div class="d-flex profile-dropdown">
          <div class="dropdown" v-show="userInfo">
            <a
              href="javascript:void(0)"
              role="button"
              data-bs-toggle="dropdown"
            >
            </a>
            &nbsp;{{ userInfo.username }}({{ userInfo.userid }}) 님 환영합니다.
            <ul class="dropdown-menu dropdown-menu-end shadow border-0 m-0 p-3">
              <li>
                <router-link
                  class="dropdown-item py-2 rounded"
                  :to="{ name: 'mypage' }"
                  ><i class="fa fa-user me-3"></i>마이 페이지</router-link
                >
              </li>
              <li>
                <a class="dropdown-item py-2 rounded" @click="onClickLogout"
                  ><i class="fa fa-info-circle me-3"></i>로그아웃</a
                >
              </li>
            </ul>
          </div>
          <div class="isNotAuth" v-show="!userInfo">
            <ul class="navbar-nav">
              <li class="ms-lg-2 ms-md-1 nav-item">
                <router-link class="nav-link" :to="{ name: 'signIn' }">
                  로그인</router-link
                >
              </li>
              <li class="ms-lg-2 ms-md-1 nav-item">
                <router-link class="nav-link" :to="{ name: 'signUp' }">
                  회원가입</router-link
                >
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "NavBar",
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
};
</script>
<style></style>
