import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

// DB에서 시도 리스트를 가져온다.
function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

// DB에서 선택한 시도에 맞는 구군 리스트를 가져온다.
function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

// DB에서 선택한 구군에 맞는 아파트 리스트를 페이지 수에 맞춰 가져온다.
function houseList(params, success, fail) {
  house.get(`/apt/${params.dong}/${params.page}`).then(success).catch(fail);
}

// DB에서 선택한 구군에 있는 아파트 리스트 수를 가져온다.
function countApt(dong, success, fail) {
  house.get(`/cnt/${dong}`).then(success).catch(fail);
}

// DB에서 userid에 저장된 북마크 리스트를 가져온다.
function bookmarkList(userid, success, fail) {
  house.get(`/map/bookmark/${userid}`).then(success).catch(fail);
}

// DB에 있는 북마크 리스트에 추가
function addBookmark(params, success, fail) {
  api.post(`/map/add`, JSON.stringify(params)).then(success).catch(fail);
}

// DB에 있는 북마크 리스트 중 userid와 aptCode가 맞는 것을 찾아 삭제
function removeBookmark(p, success, fail) {
  api.delete(`/map/remove/${p.aptCode}/${p.userid}`).then(success).catch(fail);
}

// DB에서 북마크 되어 있는 여부를 판단
function bookmarked(p, success, fail) {
  api.get(`/map/find/${p.aptCode}/${p.userid}`).then(success).catch(fail);
}

// 북마크된 갯수를 받아옴
function bookmarkCount(userid, success, fail) {
  api.get(`/map/bmcount/${userid}`).then(success).catch(fail);
}

// DB에 예약 일정을 추가
function addReserve(params, success, fail) {
  api.post(`/map/reserve`, JSON.stringify(params)).then(success).catch(fail);
}

// DB에서 예약 여부를 판단
function reserved(p, success, fail) {
  api.get(`/map/rfind/${p.aptCode}/${p.userid}`).then(success).catch(fail);
}

// DB에서 userid와 aptCode가 맞는 예약을 찾아 삭제
function removeReserve(p, success, fail) {
  api
    .delete(`/map/rmreserve/${p.aptCode}/${p.userid}`)
    .then(success)
    .catch(fail);
}

export {
  sidoList,
  gugunList,
  houseList,
  addBookmark,
  bookmarked,
  removeBookmark,
  bookmarkList,
  countApt,
  addReserve,
  reserved,
  removeReserve,
  bookmarkCount,
};
