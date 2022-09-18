<template>
  <v-container class="text-center">
    <v-alert class="text-left" dense type="info" style="font-size: 16px">
      {{ house.aptName }}
    </v-alert>
    <v-card>
      <kakao-map></kakao-map>
      <v-list>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-apps</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ house.aptCode }}</v-list-item-title>
            <v-list-item-subtitle>일련번호</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-map-marker</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>
              {{ house.sidoName }} {{ house.gugunName }}
              {{ house.dongName }}
            </v-list-item-title>
            <v-list-item-subtitle>법정동 주소</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-stairs-box</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ house.floor }}층</v-list-item-title>
            <v-list-item-subtitle>층수</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo">mdi-currency-usd</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>
              {{ (parseInt(house.money.replace(",", "")) * 10000) | price }}원
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
          color="primary"
          class="m-1"
          style="font-size: 15px"
          @click="addButton"
          v-if="isBookmarked"
        >
          <v-icon left>mdi-plus-circle-outline</v-icon>
          관심매물 등록
        </v-btn>
        <v-btn
          color="error"
          class="m-1"
          style="font-size: 15px"
          @click="removeButton"
          v-else
        >
          <v-icon left>mdi-cancel</v-icon>
          관심매물 해제
        </v-btn>
        <div v-if="!isBookmarked">
          <v-divider></v-divider>

          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>
                관심 매물 탭에서 방문 예약을 설정할 수 있습니다.
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </div>
      </v-list>
    </v-card>
    <br />
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import KakaoMap from "@/components/house/KakaoMap.vue";
import {
  addBookmark,
  bookmarkCount,
  bookmarked,
  removeBookmark,
} from "@/api/house";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      isBookmarked: false,
      bookmarkMax: false,
    };
  },
  components: {
    KakaoMap,
  },
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(memberStore, ["userInfo"]),
  },

  // 거래금액 3자릿 수마다 ','삽입
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  updated() {
    bookmarked(
      {
        aptCode: this.house.aptCode,
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
    bookmarkCount(
      this.userInfo.userid,

      ({ data }) => {
        if (data === 10) {
          this.bookmarkMax = true;
        } else {
          this.bookmarkMax = false;
        }
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    datepickButton() {
      this.$router.replace("datepickerhome");
    },
    removeButton() {
      removeBookmark(
        {
          aptCode: this.house.aptCode,
          userid: this.userInfo.userid,
        },

        ({ data }) => {
          let msg = "작업 처리중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "관심매물 해제가 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        },
      );
      this.isBookmarked = true;
    },

    addButton() {
      if (this.bookmarkMax) {
        alert("관심 매물은 최대 10개까지 지정 가능합니다.");
      } else {
        addBookmark(
          {
            aptCode: this.house.aptCode,
            userid: this.userInfo.userid,
            sidoName: this.house.sidoName,
            gugunName: this.house.gugunName,
          },

          ({ data }) => {
            let msg = "작업 처리중 문제가 발생했습니다.";
            if (data === "success") {
              msg = "관심매물 등록이 완료되었습니다.";
            }
            alert(msg);
          },
          (error) => {
            console.log(error);
          },
        );
        this.isBookmarked = false;
      }
    },
  },
};
</script>

<style></style>
