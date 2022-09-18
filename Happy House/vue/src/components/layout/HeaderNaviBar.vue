<template>
  <v-card class="overflow-hidden">
    <v-app-bar
      absolute
      color="#6A76AB"
      dark
      shrink-on-scroll
      prominent
      src="@/assets/housenav.jpg"
      fade-img-on-scroll
      scroll-target="#scrolling-techniques-4"
    >
      <template v-slot:img="{ props }">
        <v-img
          v-bind="props"
          gradient="to top right, rgba(100,115,201,.7), rgba(25,32,72,.7)"
        ></v-img>
      </template>

      <v-toolbar-title>
        <router-link
          :to="{ name: 'home' }"
          class="link"
          style="color: white; text-decoration-line: none"
        >
          Happy House
        </router-link>
      </v-toolbar-title>

      <template v-slot:extension>
        <v-tabs align-with-title v-if="userInfo">
          <v-tab>
            <router-link
              :to="{ name: 'board' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              공지사항
            </router-link>
          </v-tab>
          <v-tab>
            <router-link
              :to="{ name: 'house' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              아파트정보
            </router-link>
          </v-tab>
          <v-tab>
            <router-link
              :to="{ name: 'bookmark' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              관심 매물
            </router-link>
          </v-tab>
          <!-- <v-tab>
            <router-link
              :to="{ name: 'reservelist' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              예약 목록
            </router-link>
          </v-tab> -->
          <v-tab>
            <router-link
              :to="{ name: 'mypage' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              내 정보
            </router-link>
          </v-tab>
          <v-tab
            href="#"
            @click.prevent="onClickLogout"
            style="color: white; text-decoration-line: none"
          >
            로그아웃
          </v-tab>
        </v-tabs>
        <v-tabs align-with-title v-else>
          <v-tab>
            <router-link
              :to="{ name: 'board' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              공지사항
            </router-link>
          </v-tab>
          <v-tab>
            <router-link
              :to="{ name: 'house' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              아파트정보
            </router-link>
          </v-tab>
          <v-tab>
            <router-link
              :to="{ name: 'signUp' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              회원가입
            </router-link>
          </v-tab>
          <v-tab>
            <router-link
              :to="{ name: 'signIn' }"
              class="link"
              style="color: white; text-decoration-line: none"
            >
              로그인
            </router-link>
          </v-tab>
        </v-tabs>
      </template>
    </v-app-bar>
    <v-sheet
      id="scrolling-techniques-4"
      class="overflow-y-auto"
      max-height="220"
    >
      <v-container style="height: 220px"></v-container>
    </v-sheet>
  </v-card>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      alert("로그아웃 되었습니다.");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
