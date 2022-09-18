<template>
  <v-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <v-col cols="2" class="text-center align-self-center">
      <v-icon color="indigo">mdi-format-list-bulleted</v-icon>
    </v-col>
    <v-col class="font-weight-regular" cols="10">
      <subtitle-1 style="color: #9e9e9e">
        {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
      </subtitle-1>
      <h5>
        {{ house.aptName }}
      </h5>
    </v-col>
  </v-row>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
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
