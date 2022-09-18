<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert border="left" color="#7986CB" dark style="font-size: 19px">
          회원정보 수정
        </v-alert>
      </v-col>
    </v-row>
    <v-row class="text-center">
      <member-input-item type="modify" />
    </v-row>
  </v-container>
</template>

<script>
import MemberInputItem from "@/components/user/item/MemberInputItem.vue";

export default {
  name: "MemberModify",
  components: {
    MemberInputItem,
  },
};
</script>

<style></style>
