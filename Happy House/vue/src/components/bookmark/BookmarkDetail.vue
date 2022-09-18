<template>
  <v-container class="text-center">
    <v-alert class="text-left" dense type="info" style="font-size: 16px">
      {{ bookmark.aptName }}
    </v-alert>
    <v-card>
      <kakao-map></kakao-map>
      <v-list>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-apps</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ bookmark.aptCode }}</v-list-item-title>
            <v-list-item-subtitle>일련번호</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-map-marker</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>
              {{ bookmark.sidoName }} {{ bookmark.gugunName }}
              {{ bookmark.dongName }}
            </v-list-item-title>
            <v-list-item-subtitle>법정동 주소</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-stairs-box</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ bookmark.floor }}층</v-list-item-title>
            <v-list-item-subtitle>층수</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-currency-usd</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>
              {{
                (parseInt(bookmark.money.replace(",", "")) * 10000) | price
              }}원
            </v-list-item-title>
            <v-list-item-subtitle>거래금액</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <div v-if="isReserved">
          <v-divider></v-divider>

          <v-list-item>
            <v-list-item-icon>
              <v-icon color="indigo">mdi-calendar-text</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ date }}</v-list-item-title>
              <v-list-item-subtitle>예약일</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </div>

        <!-- <b-button variant="primary" class="m-1">주변 상권</b-button> -->
        <br />
        <v-btn
          color="error"
          class="m-1"
          style="font-size: 15px"
          @click="removeButton"
        >
          <v-icon left>mdi-cancel</v-icon>
          관심매물 해제
        </v-btn>
        <v-btn
          color="primary"
          class="m-1"
          style="font-size: 15px"
          @click="datepickButton"
          type="bookmark"
          v-if="!isReserved"
        >
          <v-icon left>mdi-calendar-text</v-icon>
          방문 예약
        </v-btn>
        <v-btn
          color="error"
          class="m-1"
          style="font-size: 15px"
          @click="quitButton"
          type="bookmark"
          v-else
        >
          <v-icon left>mdi-cancel</v-icon>
          예약 해제
        </v-btn>
      </v-list>
    </v-card>
    <br />
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import KakaoMap from "@/components/bookmark/KakaoMapBM.vue";
import {
  bookmarked,
  removeBookmark,
  reserved,
  removeReserve,
} from "@/api/house";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      isBookmarked: false,
      isReserved: false,
      date: "",
    };
  },
  components: {
    KakaoMap,
  },
  name: "bookmarkDetail",
  computed: {
    ...mapState(houseStore, ["bookmark"]),
    ...mapState(memberStore, ["userInfo"]),
    // bookmark() {
    //   return this.$store.state.bookmark;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  updated() {
    bookmarked(
      {
        aptCode: this.bookmark.aptCode,
        userid: this.userInfo.userid,
      },

      ({ data }) => {
        if (data === 0) {
          this.isBookmarked = true;
        } else {
          this.isBookmarked = false;
        }
      },
      (error) => {
        console.log(error);
      },
    );

    reserved(
      {
        aptCode: this.bookmark.aptCode,
        userid: this.userInfo.userid,
      },

      ({ data }) => {
        if (!data) {
          this.isReserved = false;
        } else {
          this.date = data;
          this.isReserved = true;
        }
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    datepickButton() {
      this.$router.replace("datepicker");
    },
    removeButton() {
      removeBookmark(
        {
          aptCode: this.bookmark.aptCode,
          userid: this.userInfo.userid,
        },

        ({ data }) => {
          let msg = "작업 처리중 문제가 발생했습니다.";
          if (data === "success") {
            this.isBookmarked = true;
            msg = "관심매물 해제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.replace("bookmark");
          this.$router.go();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    quitButton() {
      removeReserve(
        {
          aptCode: this.bookmark.aptCode,
          userid: this.userInfo.userid,
        },

        ({ data }) => {
          let msg = "작업 처리중 문제가 발생했습니다.";
          if (data === "success") {
            this.isReserved = false;
            msg = "예약이 해제되었습니다.";
          }
          alert(msg);
          this.$router.replace("bookmark");
          this.$router.go();
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>
