<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert border="left" color="#7986CB" dark style="font-size: 19px">
          회원가입
        </v-alert>
      </v-col>
    </v-row>
    <v-row class="text-center">
      <member-input-item type="register" />
    </v-row>
  </v-container>
</template>

<script>
import MemberInputItem from "@/components/user/item/MemberInputItem.vue";

export default {
  name: "MemberRegister",
  components: {
    MemberInputItem,
  },
};
</script>

<style></style>
