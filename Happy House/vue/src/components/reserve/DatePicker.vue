<template>
  <b-container class="bv-example-row mt-3 text-center">
    <v-alert
      border="top"
      colored-border
      type="info"
      color="#7986CB"
      elevation="2"
    >
      예약은 방문 3일 전부터 세 달간 가능합니다.
    </v-alert>
    <b-row align-h="center" style="float: none; margin: 0 auto">
      <VDatePicker
        is-expanded
        v-model="picker"
        locale="ko-KR"
        class="mt-4"
        title="title"
        style="font-size: 18px"
        :min-date="min"
        :max-date="max"
      ></VDatePicker>
    </b-row>
    <br />
    <v-btn
      color="primary"
      class="m-1"
      style="font-size: 15px"
      @click="datepickButton"
      type="bookmark"
      v-if="!isReserved"
    >
      <v-icon left>mdi-calendar-text</v-icon>
      예약하기
    </v-btn>
    <v-btn color="error" class="m-1" style="font-size: 15px" @click="movePrev">
      <v-icon left>mdi-backspace</v-icon>
      이전으로
    </v-btn>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import VDatePicker from "v-calendar/lib/components/date-picker.umd";
import { addReserve } from "@/api/house";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: {
    VDatePicker,
  },
  computed: {
    ...mapState(houseStore, ["bookmark"]),
    ...mapState(memberStore, ["userInfo"]),
    // bookmark() {
    //   return this.$store.state.bookmark;
    // },
  },
  data() {
    const now = new Date(Date.now() - new Date().getTimezoneOffset() * 60000);
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    // 15th two months prior
    const minDate = new Date(today);
    //minDate.setMonth(minDate.getMonth() - 2);
    minDate.setDate(now.getDate() + 3);
    // 15th in two months
    const maxDate = new Date(today);
    maxDate.setMonth(maxDate.getMonth() + 2);
    maxDate.setDate(now.getDate());

    return {
      picker: minDate,
      min: minDate,
      max: maxDate,
    };
  },
  methods: {
    datepickButton() {
      let rDate = new Date(this.picker - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10);

      addReserve(
        {
          aptCode: this.bookmark.aptCode,
          userid: this.userInfo.userid,
          reserveDate: rDate,
        },
        ({ data }) => {
          let msg = "작업 처리중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "예약이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        },
      );
      this.$router.replace("bookmark");
    },
    movePrev() {
      this.$router.replace("bookmark");
    },
  },
};
</script>

<style></style>
