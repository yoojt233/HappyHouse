<template>
  <b-tr>
    <b-td>{{ articleno }}</b-td>
    <b-th class="text-left">
      <router-link
        style="color: #424242; text-decoration-line: none"
        :to="{ name: 'boardDetail', params: { articleno: articleno } }"
      >
        {{ subject }}
      </router-link>
    </b-th>
    <!-- 댓글 수 -->
    <b-td>{{ hit }}</b-td>
    <!-- 작성자 -->
    <b-td>{{ userid }}</b-td>
    <!-- 등록 일자 -->
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
