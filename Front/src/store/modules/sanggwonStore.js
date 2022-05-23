import { dongList, gugunList, typeList } from "@/api/sanggwon.js";
// import http from "@/util/http-common";

const sanggwonStore = {
  namespaced: true,
  state: {
    gu: [],
    dong: [],
    type: [],
    houses: [],
    dealinfo: [],
  },
  getters: {},
  mutations: {
    SET_GUGUN_LIST: (state, guguns) => {
      state.gu = guguns;
    },
    SET_DONG_LIST: (state, dongs) => {
      state.dong = dongs;
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_TYPE_LIST: (state, types) => {
      state.types = types;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    CLEAR_DONG_LIST(state) {
      state.dong = [];
    },
    CLEAR_APT_LIST(state) {
      state.apt = [];
    },
    SET_APTDEAL_LIST: (state, deal) => {
      state.dealinfo = deal;
    },
  },

  actions: {
    getGugun: ({ commit }, sidocode) => {
      const params = {
        sido: sidocode,
      };
      console.log(sidocode);
      gugunList(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, guguncode) => {
      const params = {
        gugun: guguncode,
      };
      console.log(guguncode);
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getType: ({ commit }, params) => {
      console.log(params);
      typeList(
        params,
        ({ data }) => {
          commit("SET_TYPE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default sanggwonStore;
