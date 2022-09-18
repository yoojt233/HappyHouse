<template>
  <v-container
    v-if="bookmarks && bookmarks.length != 0"
    class="bv-example-row mt-3"
  >
    <v-alert
      border="top"
      colored-border
      type="info"
      color="#7986CB"
      elevation="2"
    >
      관심 매물은 최대 10개 지정 가능합니다.
    </v-alert>
    <bookmark-list-item
      v-for="(bookmark, index) in bookmarks"
      :key="index"
      :bookmark="bookmark"
    />
  </v-container>
  <v-container v-else class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <v-alert class="text-left" dense type="info" style="font-size: 16px">
          관심 매물 목록이 없습니다.
        </v-alert>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import BookmarkListItem from "@/components/bookmark/BookmarkListItem.vue";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "BookmarkList",
  components: {
    BookmarkListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["bookmarks"]),
    ...mapState(memberStore, ["userInfo"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    ...mapActions(houseStore, ["getBookmarkList"]),
  },
  created() {
    this.getBookmarkList(this.userInfo.userid);
  },
};
</script>

<style></style>
