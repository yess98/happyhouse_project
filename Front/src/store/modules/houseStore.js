import { dongList, gugunList, aptList, aptDealList } from "@/api/house.js";
// import http from "@/util/http-common";

const houseStore = {
  namespaced: true,
  state: {
    gu: [],
    dong: [],
    apt: [],
    houses: [],
    house: null,
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
    SET_APT_LIST: (state, apt) => {
      state.apt = apt;
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
    getApt: ({ commit }, dongcode) => {
      const params = {
        dong: dongcode,
      };
      aptList(
        params,
        ({ data }) => {
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getAptDeal: ({ commit }, aptCode) => {
      const params = {
        houseNo: aptCode,
      };
      aptDealList(
        params,
        ({ data }) => {
          commit("SET_APTDEAL_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default houseStore;
