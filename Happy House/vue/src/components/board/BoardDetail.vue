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
          게시글
          <br />
        </v-alert>
      </v-col>
    </v-row>

    <v-row class="mb-1">
      <v-col>
        <b-card
          :header-html="`<br><h3>${article.subject}</h3><hr>
					<div><h6>${article.userid} | ${article.regtime}</h6></div>`"
          class="text-left"
          no-body
          style="background-color: white"
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn
          color="success"
          class="m-1"
          style="font-size: 15px"
          @click="listArticle"
        >
          글목록
        </v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn
          color="primary"
          class="m-1"
          style="font-size: 15px"
          @click="moveModifyArticle"
        >
          글수정
        </v-btn>
        <v-btn
          color="error"
          class="m-1"
          style="font-size: 15px"
          @click="deleteArticle"
        >
          글삭제
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-alert
          class="text-left"
          border="left"
          color="#7986CB"
          dark
          style="font-size: 19px"
        >
          댓글
          <br />
        </v-alert>
      </v-col>
    </v-row>
    <comment-view
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
      @m-comment="onModifyComment"
    />

    <comment-write
      v-if="isModifyShow && this.modifyComment != null"
      :modifyComment="this.modifyComment"
      @modify-comment-cancel="onModifyCommentCancel"
    />
    <comment-write v-else :articleno="this.articleno" />
  </v-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/board";
import { getComment } from "@/api/comment";

import CommentWrite from "@/components/board/comment/CommentWrite.vue";
import CommentView from "@/components/board/comment/CommentView.vue";

import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      isModifyShow: false,
      modifyComment: Object,
      comments: [],
    };
  },
  components: {
    CommentWrite,
    CommentView,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );

    getComment(
      this.$route.params.articleno,
      (response) => {
        this.comments = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      //글쓴이와 동일할 때만 작동
      if (this.article.userid === this.userInfo.userid) {
        this.$router.replace({
          name: "boardModify",
          params: { articleno: this.article.articleno },
        });
      } else {
        alert("작성자 본인만 수정 가능합니다.");
      }
    },
    deleteArticle() {
      //글쓴이와 동일할 때만 작동
      if (this.article.userid === this.userInfo.userid) {
        if (confirm("정말로 삭제?")) {
          deleteArticle(this.article.articleno, () => {
            this.$router.push({ name: "boardList" });
          });
        }
      } else {
        alert("작성자 본인만 삭제 가능합니다.");
      }
    },
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
