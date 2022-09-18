<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-row>
      <b-col>
        <b-alert show><h3>예약 목록</h3></b-alert>
      </b-col>
    </b-row>
    <!-- <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row> -->
    <b-row>
      <reserve-list />
    </b-row>
  </b-container>
</template>
<script>
//import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import ReserveList from "@/components/reserve/ReserveList.vue";

export default {
  name: "ReserveView",
  components: {
    //HouseSearchBar,
    ReserveList,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
