<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert border="left" color="#7986CB" dark style="font-size: 19px">
          로그인
        </v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card class="text-center">
          <div>
            <br />
          </div>
          <v-text-field
            style="margin: 15px"
            id="userid"
            v-model="user.userid"
            label="ID"
            @keyup.enter="confirm"
            required
          ></v-text-field>
          <v-text-field
            style="margin: 15px"
            id="userpwd"
            v-model="user.userpwd"
            label="비밀번호"
            type="password"
            @keyup.enter="confirm"
            required
          ></v-text-field>
          <v-btn
            color="primary"
            class="m-1"
            style="font-size: 15px"
            @click="confirm"
          >
            로그인
          </v-btn>
          <v-btn
            color="success"
            class="m-1"
            style="font-size: 15px"
            @click="movefindPage"
          >
            비밀번호 찾기
          </v-btn>
          <v-btn
            color="error"
            class="m-1"
            style="font-size: 15px"
            @click="movePage"
          >
            회원가입
          </v-btn>
          <div>
            <br />
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        alert("로그인 되었습니다.");
        this.$router.push({ name: "home" });
      } else {
        alert("아이디 또는 비밀번호를 확인하세요.");
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
    movefindPage() {
      this.$router.push({ name: "find" });
    },
  },
};
</script>

<style></style>
