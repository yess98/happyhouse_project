import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(`/map/dong`, { params: params }).then(success).catch(fail);
}
function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}
function aptList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}
function aptDealList(params, success, fail) {
  api.get(`/map/apt/deal`, { params: params }).then(success).catch(fail);
}
function searchList(params, success, fail) {
  api.get(`/map/apt/search`, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  houseList,
  dongList,
  aptList,
  aptDealList,
  searchList,
};
