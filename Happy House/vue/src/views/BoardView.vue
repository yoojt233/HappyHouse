<template>
  <v-container class="text-center">
    <!-- <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon> Board Service
    </h3> -->
    <router-view></router-view>
  </v-container>
</template>
<script>
export default {
  name: "BoardView",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
