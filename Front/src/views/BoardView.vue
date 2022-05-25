<template>
  <div>
    <CommonHeader name="공지사항" />
    <router-view></router-view>
  </div>
</template>
<script>
import CommonHeader from "@/components/layout/CommonHeader.vue";
export default {
  name: "BoardView",
  components: {
    CommonHeader,
  },
};
</script>
<style scoped></style>
