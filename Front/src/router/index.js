import Vue from "vue";
import VueRouter from "vue-router";
// import HomeView from "../views/HomeView.vue";
import store from "@/store/index.js";
// import MainView from "@/components/MainView.vue";
import MainView from "@/views/FirstView.vue";
Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "MainView",
    component: MainView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
      {
        path: "usermodify",
        name: "usermodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberModify.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList_1.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardRegister1.vue"),
      },
      {
        path: "detail",
        name: "boardDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail1.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
    ],
  },
  {
    path: "/instagram",
    name: "instagram",
    component: () => import("@/views/InstagramView.vue"),
  },
  {
    path: "/house",
    name: "house",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/todo",
    name: "todo",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/TodoView.vue"),
  },
  {
    path: "/apt",
    name: "apt",
    component: () => import("@/views/AptView.vue"),
  },
  {
    path: "/sanggwon",
    name: "sanggwon",
    component: () => import("@/views/SanggwonView.vue"),
  },
  {
    path: "/userlist",
    name: "userlist",
    component: () => import("@/views/UserView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
