<template>
  <div class="regist">
    <div v-if="this.modifyComment != null" class="regist_form">
      <p>{{ user_name }}님의 댓글 수정</p>
      <hr />
      <textarea
        id="modicomment"
        name="modicomment"
        v-model="modicomment"
        cols="35"
        rows="2"
      ></textarea>
      <!-- <textarea id="comment" name="comment" v-text="modifyComment.comment" ref="comment" cols="35" rows="2">
      </textarea> -->
      <div>
        <br />
      </div>
      <div class="cbtn">
        <v-btn color="black" plain @click="updateComment">수정</v-btn>
        <v-btn color="error" plain @click="updateCommentCancel">취소</v-btn>
        <!-- <label @click="modifyCommentView">수정</label> |
      <label @click="deleteComment">삭제</label> -->
      </div>
    </div>
    <div v-else class="regist_form">
      <p>{{ user_name }}님의 댓글 작성</p>
      <hr />
      <textarea
        id="comment"
        name="comment"
        v-model="comment"
        cols="35"
        rows="2"
      ></textarea>
      <div>
        <br />
      </div>
      <v-btn class="cbtn" color="black" plain @click="registComment">
        등록
      </v-btn>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "comment-write",
  data() {
    return {
      user_name: "",
      comment: "",
      comment_no: "",
      modicomment: "",
      //modicomment: this.modifyComment?.comment, //props 는 직접 변경 X
    };
  },
  props: {
    articleno: { type: Number },
    modifyComment: { type: Object },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.modicomment) {
      this.user_name = this.modifyComment?.user_name;
    } else {
      this.user_name = this.userInfo.userid;
    }
  },
  methods: {
    // 댓글 등록
    registComment() {
      http
        .post(`/comment/`, {
          user_name: this.user_name,
          comment: this.comment,
          articleno: this.$route.params.articleno,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);

          // 작성글 지우기
          this.comment = "";

          // 도서평(댓글) 얻기.
          this.$router.go();
        });
    },
    // 댓글 수정
    updateComment() {
      http
        .put(`/comment`, {
          comment_no: this.modifyComment.comment_no,
          comment: this.modicomment,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);

          // 도서평(댓글) 얻기.
          this.$router.go();

          this.$emit("modify-comment-cancel", false);
        });
    },
    // 댓글 수정 취소
    updateCommentCancel() {
      this.$emit("modify-comment-cancel", false);
    },
  },
};
</script>

<style scoped>
.regist {
  padding: 10px;
}
.regist_form {
  text-align: left;
  position: relative;
  border-radius: 5px;
  background-color: #e0e0e0;
  padding: 20px;
}

textarea {
  width: 90%;
  padding: 10px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: large;
}

button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}

.cbtn {
  display: flex;
  position: absolute;
  bottom: 0;
  right: 0;
}

#comment {
  font-size: medium;
}
</style>
