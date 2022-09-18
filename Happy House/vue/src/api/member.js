import { apiInstance } from "./index.js";

const api = apiInstance();

// user를 가지고 DB에서 데이터 비교
async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

// user 회원 가입
function joinMember(user, success, fail) {
  api.post(`/user/regist`, JSON.stringify(user)).then(success).catch(fail);
}

// userid를 DB에서 중복되는지 판별
function dupMember(userid, success, fail) {
  api.get(`/user/duplicate/${userid}`).then(success).catch(fail);
}

// userid와 name이 일치할 때 비밀번호를 찾아옴
function findMember(p, success, fail) {
  api.get(`/user/find/${p.userid}/${p.username}`).then(success).catch(fail);
}

// user 회원 정보 수정
function modiMember(user, success, fail) {
  api.put(`/user/modify`, JSON.stringify(user)).then(success).catch(fail);
}

// user 회원 탈퇴
function quitMember(userid, success, fail) {
  api.delete(`/user/resign/${userid}`).then(success).catch(fail);
}

// email로 regKey 전송
function sendMail(p, success, fail) {
  api.get(`/mail/${p.email}/${p.regKey}`).then(success).catch(fail);
}

// function logout(success, fail)

export {
  login,
  findById,
  joinMember,
  dupMember,
  findMember,
  modiMember,
  quitMember,
  sendMail,
};
