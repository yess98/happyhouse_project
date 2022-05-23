<template>
  <div id="AreaName">
    <wordcloud
      :data="defaultWords"
      nameKey="word"
      valueKey="count"
      :color="myColors"
      :showTooltip="true"
      :wordClick="wordClickHandler"
      font-family="Anton"
      font-weight="Bold"
    >
    </wordcloud>
  </div>
</template>

<script>
import wordcloud from "vue-wordcloud";
import http from "@/util/http-common";

export default {
  name: "AreaName",
  components: {
    wordcloud,
  },
  methods: {
    wordClickHandler(word, count, vm) {
      console.log("wordClickHandler", word, count, vm);
    },
  },
  data() {
    return {
      myColors: ["#1f77b4", "#629fc9", "#94bedb", "#c9e0ef"],
      defaultWords: [],
    };
  },
  created() {
    http
      .get("/user/areaname")
      .then(({ data }) => {
        data.forEach((el) => {
          this.defaultWords.push({ word: el.word, count: el.count });
        });
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
