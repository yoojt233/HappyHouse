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
      </v-list>
    </v-card>
    <br />
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import KakaoMap from "@/components/bookmark/KakaoMapBM.vue";

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
};
</script>

<style></style>
