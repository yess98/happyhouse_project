<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">기본 정보</h1>
          <div class="card p-3">
            <div
              class="d-flex flex-wrap flex-md-nowrap justify-content-between"
            >
              <div class="doctor-avatar order-0 order-md-1">
                <button
                  @click="modifyInfo"
                  style="font-size: 14px"
                  class="btn px-2 py-1 btn-animate-2 fill"
                >
                  정보 수정
                </button>
              </div>
              <div class="doctor-detail order-1 order-md-0">
                <ul class="resume-box">
                  <li>
                    <div class="icon text-center">
                      <i
                        class="bi bi-person-badge-fill"
                        style="font-size: 1.3rem"
                      ></i>
                    </div>
                    <div class="fw-bold mb-0">아이디</div>
                    <span>{{ userInfo.userid }}</span>
                  </li>
                  <li>
                    <div class="icon text-center">
                      <i
                        class="bi bi-person-badge-fill"
                        style="font-size: 1.3rem"
                      ></i>
                    </div>
                    <div class="fw-bold mb-0">이름</div>
                    <span>{{ userInfo.username }}</span>
                  </li>
                  <li>
                    <div class="icon text-center">
                      <i class="fa fa-envelope"></i>
                    </div>
                    <div class="fw-bold mb-0">E-Mail</div>
                    <span>{{ userInfo.email }}</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <button
            @click="deleteUser"
            style="font-size: 14px"
            class="btn btn-danger mt-4 mb-4 px-2 py-1 lift float-end"
          >
            회원 탈퇴
          </button>
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
  name: "ProfileView",
  computed: {
    ...mapState(storeName, ["userInfo"]),
  },
  methods: {
    ...mapMutations(storeName, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    modifyInfo() {
      this.$swal({
        text: "비밀번호를 입력해 주세요.",
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        buttons: true,
      }).then((value) => {
        if (value) {
          if (value === this.userInfo.userpwd) {
            console.log(value);
            this.$router.push({ name: "usermodify" });
          } else {
            this.$swal("비밀번호가 일치하지 않습니다.").then(() =>
              this.modifyInfo(),
            );
          }
        } else if (value == "") {
          this.$swal("비밀번호를 입력하세요!").then(() => this.modifyInfo());
        }
      });
    },
    deleteUser() {
      this.$swal({
        title: "정말 탈퇴하시겠습니까?",
        text: "탈퇴하시려면 비밀번호를 입력해 주세요.",
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        icon: "warning",
        dangerMode: true,
        buttons: true,
      }).then((value) => {
        if (value) {
          console.log(value);
          console.log(this.userInfo);
          if (value === this.userInfo.userpwd) {
            http
              .delete("/user/" + this.userInfo.userid)
              .then((response) => {
                console.log(response.data);
                if (response.data === "success") {
                  this.$swal(
                    "탈퇴 처리가 완료되었습니다.",
                    "지금까지 이용해주셔서 감사합니다.",
                    { icon: "success" },
                  ).then(() => {
                    this.SET_IS_LOGIN(false);
                    this.SET_USER_INFO(null);
                    this.$router.push({ name: "MainView" });
                  });
                } else {
                  this.$swal("처리중에 문제가 발생하였습니다.", {
                    icon: "error",
                  });
                }
              })
              .catch((error) => {
                console.log(error);
                this.$swal("서버에 문제가 발생하였습니다.", { icon: "error" });
              });
          } else {
            this.$swal("비밀번호가 일치하지 않습니다.").then(() =>
              this.deleteUser(),
            );
          }
        } else if (value == "") {
          this.$swal("비밀번호를 입력하세요!").then(() => this.deleteUser());
        }
      });
    },
  },
};
</script>
<style></style>
