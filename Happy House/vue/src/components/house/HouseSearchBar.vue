<template>
  <v-row class="mt-4 mb-4 text-center">
    <v-col class="sm-3">
      <!-- 경기도, 충청도, 경상도 ... -->
      <!-- 서울특별시, 인천광역시, 부산광역시 ... -->
      <!-- 선택 시 gugunList 할당 -->
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
        style="width: 90%"
      ></b-form-select>
    </v-col>

    <!-- 종로구, 원미구, 계양구 ... -->
    <!-- 선택 시 동에 맞는 아파트 리스트 할당 -->
    <v-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="searchApt"
        style="width: 90%"
      ></b-form-select>
    </v-col>
  </v-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "houses"]),
  },
  // component 생성 시
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),

    // 시도 선택 시 그 전의 gugunList를 초기화, 현재 선택한 시도로 재할당
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },

    // 구군 선택 시 gugunCode와 1번 페이지를 보여주기 위한 param
    searchApt() {
      if (this.gugunCode)
        this.getHouseList({ gugunCode: this.gugunCode, pageno: 1 });
    },
  },
};
</script>

<style></style>
