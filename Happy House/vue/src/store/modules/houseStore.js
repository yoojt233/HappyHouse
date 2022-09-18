import {
  sidoList,
  gugunList,
  houseList,
  bookmarkList,
  countApt,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    selected_gugun: [],
    totalCnt: [],
    houses: [],
    bookmarks: [],
    house: null,
    bookmark: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_HOUSE_GUGUN: (state, gugun) => {
      state.selected_gugun = gugun;
    },
    SET_HOUSE_CNT: (state, totalCnt) => {
      state.totalCnt = totalCnt;
    },
    SET_BOOKMARK_LIST: (state, bookmarks) => {
      //   console.log(houses);
      state.bookmarks = bookmarks;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_DETAIL_BOOKMARK: (state, bookmark) => {
      state.bookmark = bookmark;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, { gugunCode, pageno }) => {
      const params = {
        dong: gugunCode,
        page: pageno,
      };
      houseList(
        params,
        ({ data }) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
      countApt(
        params.dong,
        ({ data }) => {
          commit("SET_HOUSE_CNT", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        },
      );
      commit("SET_HOUSE_GUGUN", gugunCode);
    },
    getBookmarkList: ({ commit }, userid) => {
      bookmarkList(
        userid,
        ({ data }) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_BOOKMARK_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    detailBookmark: ({ commit }, bookmark) => {
      commit("SET_DETAIL_BOOKMARK", bookmark);
    },
  },
};

export default houseStore;
