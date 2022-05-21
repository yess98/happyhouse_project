<template>
  <b-tr>
    <b-td>{{ index + 1 }}</b-td>
    <b-td
      ><a :href="url">{{ title }}</a></b-td
    >
    <b-td><img :src="imgsrc" width="80" height="50" alt:="title"/></b-td>
  </b-tr>
</template>

<script>
export default {
  name: "NewsListItem",
  props: {
    index: Number,
    url: String,
    title: String,
    imgsrc: String,
  },
};
</script>

<style></style>
