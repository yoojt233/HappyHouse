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
          매물 예약
        </v-alert>
      </v-col>
    </v-row>
    <!-- <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row> -->
    <v-row>
      <v-col cols="6" align="left">
        <date-picker />
      </v-col>
      <v-col cols="6">
        <date-reserve />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
//import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import DatePicker from "@/components/reserve/DatePicker.vue";
import DateReserve from "@/components/reserve/DateReserve.vue";

export default {
  name: "DatepickerView",
  components: {
    DatePicker,
    DateReserve,
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
