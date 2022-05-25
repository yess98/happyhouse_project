<template>
  <div>
    <CommonHeader name="회원가입" />
    <div class="container my-5">
      <div class="d-flex flex-column align-items-center">
        <h2 class="mb-4">Happy House 가입을 환영합니다!</h2>
        <form class="row g-1 p-0 p-md-4 w-100">
          <table style="width: 100%">
            <tr>
              <td style="width: 20%" class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="username">
                  이름 <span class="text-danger">*</span>
                </label>
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
                <label class="mb-4 form-label" for="userid">
                  아이디 <span class="text-danger">*</span>
                </label>
              </td>
              <td v-if="userIdChk" class="px-4">
                <input
                  v-model="user.userid"
                  @change="idcheck"
                  id="user.userid"
                  type="text"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
              <td v-else class="px-4 form-floating">
                <input
                  v-model="user.userid"
                  @change="idcheck"
                  id="user.userid"
                  type="text"
                  class="mb-4 form-control is-invalid form-control-lg"
                />
                <label for="userid" class="ps-5 text-danger"
                  >중복된 아이디가 존재합니다. 다른 아이디를 입력하세요.</label
                >
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userpwd">
                  비밀번호 <span class="text-danger">*</span>
                </label>
              </td>
              <td class="px-4">
                <input
                  v-model="user.userpwd"
                  id="userpwd"
                  type="password"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="email">
                  이메일 <span class="text-danger">*</span>
                </label>
              </td>
              <td class="px-4">
                <input
                  v-model="user.email"
                  id="email"
                  type="email"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
            </tr>
          </table>
          <div class="col-12 text-center mt-4">
            <router-link
              :to="{ name: 'signIn' }"
              class="btn px-4 btn-block btn-secondary lift text-uppercase"
              >로그인 이동</router-link
            >&nbsp;
            <a
              @click="regist"
              class="btn px-4 btn-block btn-primary lift text-uppercase"
              >회원가입</a
            >
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import CommonHeader from "@/components/layout/CommonHeader.vue";

export default {
  name: "JoinView",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
        joindate: null,
      },
      userIdChk: true,
    };
  },
  components: {
    CommonHeader,
  },
  methods: {
    idcheck() {
      http
        .get(`/user/idcheck/${this.user.userid}`)
        .then(({ data }) => {
          let msg = "중복된 아이디입니다. 다시 입력해주세요";
          if (data === "success") {
            msg = "사용가능한 아이디입니다.";
            this.userIdChk = true;
            alert(msg);
          } else {
            alert(msg);
            this.$router.push({ name: "signUp" });
          }
        })
        .catch((error) => {
          console.log(error);
          alert("ID중복체크에 실패했습니다..");
        })
        .finally(() => {
          console.log(this.user.userid);
        });
    },
    regist() {
      http
        .post("/user/register/", {
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          username: this.user.username,
          email: this.user.email,
          joindate: this.user.joindate,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            console.log(msg);
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
          console.log(this.user.userid);
          console.log(this.user.userpwd);
          console.log(this.user.username);
        });
    },
    movePage() {
      this.$router.push({ name: "signIn" });
    },
  },
};
</script>

<style scope></style>
