import { apiInstance } from "./index.js";

const api = apiInstance();

// DB에서 pageno에 해당하는 게시판 글들 가져온다.
function listArticle(p, success, fail) {
  api
    .get(`/board/list/${p.pageno}/${p.condition}/${p.text}`)
    .then(success)
    .catch(fail);
}

// DB에 있는 게시판 글 총 갯수
function countArticle(p, success, fail) {
  api.get(`/board/count/${p.condition}/${p.text}`).then(success).catch(fail);
}

// DB에 게시판 글 작성
function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

// DB에서 articleno에 맞는 게시판 글 가져온다.
function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

// DB에서 articleno에 맞는 게시판 글 수정
function modifyArticle(article, success, fail) {
  api
    .put(`/board/${article.articleno}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

// DB에서 articleno에 맞는 게시판 글 삭제
function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  countArticle,
};
