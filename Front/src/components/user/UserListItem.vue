<template>
  <b-tr>
    <b-td>{{ index + 1 }}</b-td>
    <b-td>{{ username }}</b-td>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ email }}</b-td>
    <b-td>{{ joindate }}</b-td>
  </b-tr>
</template>

<script>
export default {
  name: "UserListItem",
  props: {
    index: Number,
    username: String,
    userid: String,
    email: String,
    joindate: String,
  },
};
</script>

<style></style>
