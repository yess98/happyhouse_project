<template>
  <div>
    <b-navbar type="light" style="height: 100%">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="60px"
            height="60px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'instagram' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="newspaper" font-scale="1.0"></b-icon>
                부동산 News
              </p></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'apt' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="house-fill" font-scale="1.0"></b-icon>
                아파트정보
              </p></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'sanggwon' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="house-fill" font-scale="1.0"></b-icon>
                상권정보
              </p></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="journal" font-scale="1.0"></b-icon>
                게시판
              </p></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'todo' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="calendar-check" font-scale="1.0"></b-icon>
                TodoList
              </p></router-link
            ></b-nav-item
          >
          <b-nav-item href="#" v-if="userInfo && userInfo.userid == `admin`"
            ><router-link :to="{ name: 'userlist' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="house-fill" font-scale="1.0"></b-icon>
                사용자목록
              </p></router-link
            ></b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center"
            ><b-avatar
              variant="primary"
              v-text="userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''"
            ></b-avatar
            >{{ userInfo.username }}({{ userInfo.userid }})님
            환영합니다.</b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'mypage' }"
              class="link align-self-center"
              ><p class="h5 mb-2">내정보보기</p></router-link
            ></b-nav-item
          >
          <b-nav-item
            class="link align-self-center"
            @click.prevent="onClickLogout"
            >로그아웃</b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="1"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key"></b-icon>
                <p>로그인</p></router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
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
};
</script>

<style></style>
