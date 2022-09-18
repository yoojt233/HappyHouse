<template>
  <v-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <v-col cols="2" class="text-center align-self-center">
      <v-icon v-if="isReserved" color="yellow">mdi-lightbulb-alert</v-icon>
      <v-icon v-else color="indigo">mdi-format-list-bulleted</v-icon>
    </v-col>
    <v-col class="font-weight-regular" cols="10">
      <subtitle-1 style="color: #9e9e9e">
        {{ bookmark.sidoName }} {{ bookmark.gugunName }} {{ bookmark.dongName }}
      </subtitle-1>
      <h5>
        {{ bookmark.aptName }}
      </h5>
    </v-col>
  </v-row>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { reserved } from "@/api/house";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "BookmarkListItem",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    // bookmark() {
    //   return this.$store.state.bookmark;
    // },
  },
  data() {
    return {
      isColor: false,
      isReserved: false,
    };
  },
  props: {
    bookmark: Object,
  },
  updated() {
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
    ...mapActions(houseStore, ["detailHouse", "detailBookmark"]),
    selectHouse() {
      console.log("listRow : ", this.bookmark);
      // this.$store.dispatch("getHouse", this.house);
      this.detailBookmark(this.bookmark);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: #e8eaf6;
}
</style>
