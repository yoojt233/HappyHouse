<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert
          border="left"
          color="#7986CB"
          dark
          style="font-size: 19px; margin: 12px"
        >
          아파트 정보
        </v-alert>
      </v-col>
    </v-row>
    <!-- <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row> -->
    <v-row>
      <v-col>
        <house-search-bar></house-search-bar>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6" align="left">
        <house-list />
      </v-col>
      <v-col cols="6">
        <house-detail />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
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
