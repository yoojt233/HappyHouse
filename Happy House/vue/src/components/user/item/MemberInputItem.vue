<template>
  <v-container>
    <v-row>
      <v-col style="text-align: left">
        <v-form @submit="onSubmit" v-model="val">
          <v-text-field
            v-if="this.type === 'modify'"
            id="userid"
            v-model="user.userid"
            disabled
            label="ID"
            required
          ></v-text-field>
          <v-text-field
            v-else
            id="userid"
            v-model="user.userid"
            :counter="16"
            :rules="inputRules"
            label="ID"
            required
          ></v-text-field>
          <v-btn
            v-if="this.type === 'register'"
            v-model="valid"
            color="primary"
            class="m-1"
            style="font-size: 15px"
            @click="dupCheck()"
          >
            중복 체크
          </v-btn>
          <br />
          <v-text-field
            id="userpwd"
            v-model="user.userpwd"
            label="비밀번호"
            :counter="16"
            :rules="inputRules"
            type="password"
            required
          ></v-text-field>
          <v-text-field
            id="userpwdre"
            v-model="user.userpwdre"
            label="비밀번호 재입력"
            :counter="16"
            :rules="inputRules"
            type="password"
            required
          ></v-text-field>
          <v-text-field
            id="username"
            v-model="user.username"
            label="이름"
            required
          ></v-text-field>
          <v-text-field
            id="email"
            v-model="user.email"
            label="이메일"
            :rules="emailRules"
            required
          ></v-text-field>
          <v-btn
            v-if="this.type === 'register'"
            type="submit"
            color="primary"
            class="m-1"
            style="font-size: 15px"
            :disabled="!val"
            @click="validate"
          >
            회원 가입
          </v-btn>
          <v-btn
            v-else
            type="submit"
            color="primary"
            class="m-1"
            style="font-size: 15px"
            :disabled="!val"
            @click="validate"
          >
            <v-icon left>mdi-pencil</v-icon>
            회원정보 수정
          </v-btn>
          <v-btn
            color="error"
            class="m-1"
            style="font-size: 15px"
            @click="goBack()"
          >
            <v-icon left>mdi-arrow-left-circle</v-icon>
            뒤로 가기
          </v-btn>
          <br />
          <br />
          <!-- 모달창 -->
          <div v-if="modalOpen == true">
            <v-alert
              border="top"
              colored-border
              type="info"
              color="#7986CB"
              elevation="2"
            >
              입력하신 이메일로 인증번호가 발급되었습니다.
              <br />
              인증번호를 입력해주세요.
            </v-alert>

            <v-text-field
              v-model="checkingNumber"
              required
              label="인증번호"
            ></v-text-field>
            <v-btn
              type="submit"
              color="primary"
              class="m-1"
              style="font-size: 15px"
              @click="checkValue"
            >
              확인
            </v-btn>
            <v-btn
              color="error"
              class="m-1"
              style="font-size: 15px"
              @click="cancelValue"
            >
              취소
            </v-btn>
          </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { joinMember, dupMember, modiMember, sendMail } from "@/api/member";
//import { findById } from "@/api/member";
import { mapState } from "vuex";

const memberStore = "memberStore";
let regKey = "";
export default {
  name: "MemberInputItem",
  data() {
    return {
      val: true,
      modalOpen: false,
      checkingNumber: "",
      user: {
        userid: "",
        userpwd: "",
        userpwdre: "",
        username: "",
        email: "",
      },
      valid: false,
      inputRules: [
        (v) => !!v || "내용을 입력해주세요.",
        (v) => (v && v.length <= 10) || "16글자 이하로 등록 가능합니다.",
      ],
      emailRules: [
        (v) => !!v || "내용을 입력해주세요.",
        (v) => /.+@.+\..+/.test(v) || "사용 가능한 이메일 형식이 아닙니다.",
      ],
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    //alert("수정");
    if (this.type === "modify") {
      this.user = this.userInfo;
    }
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    onSubmit(event) {
      event.preventDefault();

      let err = false;
      let msg = "";

      if (this.type === "register" && !this.valid) {
        msg = "중복 체크를 해주세요.";
        err = true;
      }
      if (!err && this.user.userpwdre !== this.user.userpwd) {
        msg = "비밀번호가 일치하지 않습니다.";
        err = true;
      }

      if (err) alert(msg);
      else this.type === "register" ? this.registMember() : this.modifyMember();
    },
    onReset(event) {
      event.preventDefault();

      if (this.type === "register") {
        this.user.userid = "";
      }
      this.user.userpwd = "";
      this.user.userpwdre = "";
      this.user.username = "";
      this.user.email = "";
    },
    registMember() {
      this.modalOpen = true;
      // 인증번호 생성, 이메일로 인증번호 발송
      regKey = Math.random().toString(36).slice(2);

      sendMail(
        {
          email: this.user.email,
          regKey: regKey,
        },
        ({ data }) => {
          let msg = "이메일 발송 실패";
          if (data !== "success") {
            alert(msg);
          }
        },
      );
    },
    checkValue() {
      //인증번호와 checkingNumber 비교
      if (this.checkingNumber === regKey) {
        joinMember(
          {
            userid: this.user.userid,
            userpwd: this.user.userpwd,
            username: this.user.username,
            email: this.user.email,
          },
          ({ data }) => {
            let msg = "회원 가입 중 문제가 발생했습니다.";
            if (data === "success") {
              msg = "회원 가입이 완료되었습니다.";
            }
            alert(msg);
            this.moveList();
          },
          (error) => {
            console.log(error);
          },
        );
      } else {
        let msg = "인증번호가 일치하지 않습니다. 확인해주세요.";
        alert(msg);
      }
    },
    cancelValue() {
      this.checkingNumber = "";
      this.modalOpen = false;
    },
    modifyMember() {
      modiMember(
        {
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          username: this.user.username,
          email: this.user.email,
        },
        ({ data }) => {
          let msg = "수정 중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원정보 수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /user/mypage로 변경.
          this.$router.push({ name: "mypage" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "signIn" });
    },

    dupCheck() {
      if (!this.user.userid) {
        alert("ID를 입력해주세요");
      } else {
        dupMember(
          this.user.userid,
          ({ data }) => {
            let msg = "사용 불가능한 ID입니다.";
            if (data === "success") {
              msg = "사용 가능한 ID입니다.";
              this.valid = true;
            }
            alert(msg);
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },

    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.black-bg {
  width: 60%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
}
.white-bg {
  width: 100%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}
.button-box {
  text-align: right;
  margin: 10px;
}
</style>
