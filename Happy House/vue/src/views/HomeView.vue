<template>
  <v-container class="bv-example-row mt-3 text-center">
    <v-col>
      <v-card class="text-center">
        <v-list>
          <br />
          <h2>HAPPY HOUSE</h2>
          <br />
        </v-list>
      </v-card>
    </v-col>
    <br />
    <v-row>
      <v-col cols="4" align="left">
        <v-card class="text-center">
          <v-img src="@/assets/h2.jpeg"></v-img>
        </v-card>
      </v-col>
      <v-col cols="4">
        <v-card class="text-center">
          <v-img src="@/assets/h3.jpeg"></v-img>
        </v-card>
      </v-col>
      <v-col cols="4">
        <v-card class="text-center">
          <v-img src="@/assets/h1.jpg"></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
