<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert
          class="text-left"
          border="left"
          color="#7986CB"
          dark
          style="font-size: 19px"
        >
          게시글 작성
        </v-alert>
      </v-col>
    </v-row>
    <board-input-item type="register" />
  </v-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
