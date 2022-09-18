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
          공지사항
        </v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="text-right">
        <v-btn
          color="primary"
          class="m-1"
          style="font-size: 15px"
          @click="moveWrite()"
        >
          글쓰기
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="articles.length">
        <b-table-simple
          hover
          responsive
          id="my-table"
          :per-page="perPage"
          :current-page="currentPage"
        >
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>댓글수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </v-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </v-row>
    <br />
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
      @page-click="pageClick"
    ></b-pagination>
    <b-input-group class="mt-3">
      <b-form-select v-model="selected" :options="options"></b-form-select>
      <b-form-input v-model="condition"></b-form-input>
      <b-input-group-append>
        <b-button variant="outline-primary" @click="conditionSearch()">
          검색
        </b-button>
      </b-input-group-append>
    </b-input-group>
  </v-container>
</template>

<script>
import { listArticle, countArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";

import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      selected: "null",
      condition: null,
      options: [
        { value: "null", text: "검색조건을 선택하세요." },
        { value: "subject", text: "제목" },
        { value: "content", text: "내용" },
        { value: "userid", text: "작성자" },
      ],
      perPage: 10,
      currentPage: 1,
      rows: 0,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.currentPage = 1;
    //글쓴이가 관리자 속성일 때만 글쓰기 버튼 표현
    listArticle(
      {
        pageno: this.currentPage,
        condition: this.selected,
        text: this.condition,
      },

      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
    countArticle(
      { condition: this.selected, text: this.condition },
      (response) => {
        this.rows = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    pageClick: function (button, page) {
      this.currentPage = page;
      listArticle(
        {
          pageno: this.currentPage,
          condition: this.selected,
          text: this.condition,
        },

        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveWrite() {
      if (this.userInfo && this.userInfo.admin === 1) {
        this.$router.push({ name: "boardRegister" });
      } else {
        alert("관리자만 게시글을 작성할 수 있습니다.");
      }
    },
    async conditionSearch() {
      if (this.selected === "null") {
        alert("조건을 먼저 지정해주세요.");
      } else if (!this.condition) {
        alert("검색어를 입력해주세요.");
      } else {
        listArticle(
          {
            pageno: this.currentPage,
            condition: this.selected,
            text: this.condition,
          },

          (response) => {
            this.articles = response.data;
          },
          (error) => {
            console.log(error);
          },
        );

        countArticle(
          { condition: this.selected, text: this.condition },
          (response) => {
            this.rows = response.data;
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
