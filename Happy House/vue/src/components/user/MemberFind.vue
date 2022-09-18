<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert border="left" color="#7986CB" dark style="font-size: 19px">
          비밀번호 찾기
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
            v-model="user.username"
            label="이름"
            @keyup.enter="confirm"
            required
          ></v-text-field>
          <v-btn
            color="success"
            class="m-1"
            style="font-size: 15px"
            @click="confirm"
          >
            비밀번호 찾기
          </v-btn>
          <v-btn
            color="error"
            class="m-1"
            style="font-size: 15px"
            @click="movePage"
          >
            이전으로
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
import { findMember } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberFind",
  data() {
    return {
      user: {
        userid: null,
        username: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    confirm() {
      findMember(
        {
          userid: this.user.userid,
          username: this.user.username,
        },
        ({ data }) => {
          if (!data) {
            alert("일치하는 정보가 없습니다.");
          } else {
            alert("비밀번호는 " + data + " 입니다.");
            this.$router.push({ name: "signIn" });
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    movePage() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
