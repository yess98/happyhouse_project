<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row> -->
    <b-row>
      <b-col v-if="users.length">
        <b-table-simple
          hover
          responsive
          style="background-color: blanchedalmond; text-align: center"
        >
          <b-thead head-variant="light">
            <b-tr>
              <b-th>번호</b-th>
              <b-th>이름</b-th>
              <b-th>ID</b-th>
              <b-th>Email</b-th>
              <b-th>가입시기</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <user-list-item
              v-for="(user, index) in users"
              :key="index"
              v-bind="user"
              v-bind:index="index"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import { userList } from "@/api/member.js";
import UserListItem from "@/components/user/UserListItem";

export default {
  name: "UserView",
  components: {
    UserListItem,
  },
  data() {
    return {
      users: [],
    };
  },
  created() {
    userList(
      (response) => {
        this.users = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
