<template>
  <div v-show="isShow" class="comment">
    <div class="head">{{ comment.user_name }} | {{ comment.comment_time }}</div>
    <div class="content" v-html="enterToBr(comment.comment)"></div>

    <!-- 로그인 기능 구현 후 로그인한 자신의 글에만 보이게 한다. -->
    <div class="cbtn" v-if="useControl">
      <v-btn color="black" plain @click="modifyCommentView">수정</v-btn>
      <v-btn color="error" plain @click="deleteComment">삭제</v-btn>
    </div>
    <div v-else>
      <br />
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "comment-view",
  data() {
    return {
      isShow: true,
      useControl: false,
    };
  },
  props: {
    comment: Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    //글쓴이와 동일할 때만 작동
    if (this.comment.user_name === this.userInfo.userid) {
      this.useControl = true;
    }
  },
  methods: {
    modifyCommentView() {
      this.$emit("m-comment", {
        comment_no: this.comment.comment_no,
        comment: this.comment.comment,
        articleno: this.comment.articleno,
      });
    },
    deleteComment() {
      if (confirm("삭제하시겠습니까?")) {
        http.delete(`/comment/${this.comment.comment_no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          //새로고침.
          this.$router.go();
        });
      }
    },

    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>

<style>
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #f5f5f5;
  padding: 10px 20px;
  margin: 10px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  margin: 5px 0px;
}
</style>
