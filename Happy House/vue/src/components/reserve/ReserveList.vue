<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()">
          글쓰기
        </b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple
          hover
          responsive
          id="my-table"
          :per-page="perPage"
          :current-page="currentPage"
        >
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>예약일</b-th>
              <b-th>글번호</b-th>
              <b-th>예약 매물</b-th>
              <b-th>작성자</b-th>
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
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>
  </b-container>
</template>

<script>
import { listArticle, countArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";

import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "ReserveList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      perPage: 10,
      currentPage: 1,
      rows: 0,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    this.currentPage = 1;
    countArticle(
      param,
      (response) => {
        this.rows = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  updated() {
    listArticle(
      this.currentPage,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      //글쓴이와 동일할 때만 작동
      alert(this.rows);
      if (this.userInfo.admin === 1) {
        this.$router.push({ name: "boardRegister" });
      } else {
        alert("관리자만 작성 가능합니다.");
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
