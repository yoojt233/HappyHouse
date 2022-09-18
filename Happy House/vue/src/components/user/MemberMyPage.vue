<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert border="left" color="#7986CB" dark style="font-size: 19px">
          내 정보
        </v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card class="text-center">
          <v-list>
            <v-list-item>
              <v-list-item-icon>
                <v-icon color="indigo">mdi-cards-club</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.userid }}</v-list-item-title>
                <v-list-item-subtitle>아이디</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon color="indigo">mdi-card-account-details</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>
                  {{ userInfo.username }}
                </v-list-item-title>
                <v-list-item-subtitle>이름</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon color="indigo">mdi-email</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.email }}</v-list-item-title>
                <v-list-item-subtitle>이메일</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon color="indigo">mdi-calendar-import</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.joindate }}</v-list-item-title>
                <v-list-item-subtitle>가입일</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <!-- <b-button variant="primary" class="m-1">주변 상권</b-button> -->
            <br />
            <v-btn
              color="primary"
              class="m-1"
              style="font-size: 15px"
              @click="moveModifyMember"
            >
              <v-icon left>mdi-pencil</v-icon>
              정보 수정
            </v-btn>
            <v-btn
              color="error"
              class="m-1"
              style="font-size: 15px"
              @click="resignMember"
            >
              <v-icon left>mdi-cancel</v-icon>
              회원 탈퇴
            </v-btn>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { quitMember } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveModifyMember() {
      this.$router.push({ name: "modifyUser" });
    },
    resignMember() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        quitMember(
          this.userInfo.userid,
          ({ data }) => {
            let msg = "회원 탈퇴 중 문제가 발생했습니다.";
            if (data === "success") {
              msg = "회원 탈퇴가 완료되었습니다.";

              this.SET_IS_LOGIN(false);
              this.SET_USER_INFO(null);
              sessionStorage.removeItem("access-token");
              this.$router.push({ name: "home" });
            }
            alert(msg);
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

<style></style>
