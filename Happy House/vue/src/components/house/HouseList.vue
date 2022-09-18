<template>
  <v-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
      type="house"
    />
    <div class="overflow-auto">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        @change="pageChange"
        first-number
        last-number
      ></b-pagination>
    </div>
  </v-container>
  <v-container v-else class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <v-alert class="text-left" dense type="info" style="font-size: 16px">
          주택 목록이 없습니다.
        </v-alert>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      currentPage: 1,
      perPage: 10,
      rows: 0,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "selected_gugun", "totalCnt"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  updated() {
    this.rows = this.totalCnt;
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    pageChange(currentPage) {
      this.getHouseList({
        gugunCode: this.selected_gugun,
        pageno: currentPage,
      });
    },
  },
};
</script>

<style scoped>
.overflow-auto {
  display: flex;
  justify-content: center;
}
</style>
