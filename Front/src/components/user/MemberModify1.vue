<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">정보 수정</h1>
          <div class="d-flex flex-column align-items-center">
            <form class="row g-1 p-0 p-md-4 w-100">
              <table style="width: 100%">
                <tr>
                  <td style="width: 20%" class="px-4 border-top border-dark">
                    <label class="mb-4 form-label" for="username"
                      >이름 <span class="text-danger">*</span></label
                    >
                  </td>
                  <td class="px-4">
                    <input
                      v-model="user.username"
                      id="username"
                      type="text"
                      class="mb-4 form-control form-control-lg"
                    />
                  </td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark">
                    <label class="mb-4 form-label" for="userId">
                      아이디 <span class="text-danger">*</span></label
                    >
                  </td>
                  <td class="px-4">
                    <input
                      readonly
                      v-model="userInfo.userid"
                      id="userId"
                      type="text"
                      class="mb-4 form-control form-control-lg"
                    />
                  </td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark">
                    <label class="mb-4 form-label" for="userPwd">
                      비밀번호<span class="text-danger">*</span></label
                    >
                  </td>
                  <td class="px-4">
                    <input
                      v-model="user.userpwd"
                      id="userPwd"
                      type="text"
                      class="mb-4 form-control form-control-lg"
                    />
                  </td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark">
                    <label class="mb-4 form-label" for="userId">
                      이메일 <span class="text-danger">*</span></label
                    >
                  </td>
                  <td class="px-4">
                    <input
                      v-model="user.email"
                      id="userEmail"
                      type="email"
                      class="mb-4 form-control form-control-lg"
                    />
                  </td>
                </tr>
              </table>
              <div class="col-12 text-center my-4">
                <router-link
                  :to="{ name: 'mypage' }"
                  class="btn px-4 btn-block btn-secondary lift text-uppercase"
                  >취소</router-link
                >&nbsp;
                <a
                  @click="modifyInfo"
                  class="btn px-4 btn-block btn-primary lift text-uppercase"
                  >수정 완료</a
                >
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapState, mapMutations } from "vuex";

const storeName = "memberStore";

export default {
  name: "ModifyInfo",
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
    ...mapState(storeName, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(storeName, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    modifyInfo() {
      http
        .put("/user/usermodify", {
          userid: this.userInfo.userid,
          username: this.user.username,
          email: this.user.email,
          userpwd: this.user.userpwd,
          joindate: this.user.joindate,
        })
        .then((response) => {
          console.log(response.data);
          if (response.data === "success") {
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            this.$swal("정보 수정이 완료되었습니다.", { icon: "success" }).then(
              () => this.$router.push({ name: "signIn" }),
            );
          }
        })
        .catch((error) => {
          console.log("RegisterVue: error : ");
          console.log(error);
          this.$swal("서버에 문제가 발생하였습니다.", { icon: "error" });
        });
    },
    movePage() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style></style>
