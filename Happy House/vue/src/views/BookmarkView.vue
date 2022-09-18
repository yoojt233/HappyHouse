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
          관심 매물
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
        <bookmark-list />
      </v-col>
      <v-col cols="6">
        <bookmark-detail v-if="bookmarks.length > 0" />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import BookmarkList from "@/components/bookmark/BookmarkList.vue";
import BookmarkDetail from "@/components/bookmark/BookmarkDetail.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "BookmarkView",
  components: {
    //HouseSearchBar,
    BookmarkList,
    BookmarkDetail,
  },
  computed: {
    ...mapState(houseStore, ["bookmarks"]),
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
