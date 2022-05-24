<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <!-- <b-alert variant="secondary" show><h3>회원가입</h3></b-alert> -->
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="text-center mt-3"
          style="max-width: 40rem; background-color: blanchedalmond"
          align="left"
        >
          <b-form class="text-left">
            <b-form-group label="ID:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="ID 입력"
              ></b-form-input>
            </b-form-group>
            <b-button variant="secondary" @click="idcheck"
              >아이디 중복체크</b-button
            >
            <b-form-group label="PASSWORD:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="PASWORD 입력"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="이름 입력"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="E-MAIL:" label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                required
                placeholder="E-MAIL 입력"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="regist"
              >가입하기</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >취소</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";

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
  methods: {
    idcheck() {
      http
        .get(`/user/idcheck/${this.user.userid}`)
        .then(({ data }) => {
          let msg = "중복된 아이디입니다. 다시 입력해주세요";
          if (data === "success") {
            msg = "사용가능한 아이디입니다.";
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

<style></style>
