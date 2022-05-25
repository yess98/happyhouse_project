const boardStore = {
  namespaced: true,
  state: {
    noticeId: 0,
    title: "",
    content: "",
    userName: "",
    regDt: {},
    readCount: 0,
  },
  getters: {},
  mutations: {
    SET_BOARD_DETAIL(state, payload) {
      state.noticeId = payload.articleno;
      state.title = payload.subject;
      state.content = payload.content;
      state.userName = payload.userid;
      state.readCount = payload.hit;
      state.regDt = payload.regtime;
    },
  },
  actions: {},
};

export default boardStore;
