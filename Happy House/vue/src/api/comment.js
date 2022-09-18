import { apiInstance } from "./index.js";

const api = apiInstance();

function getComment(articleno, success, fail) {
  api.get(`/comment/${articleno}`).then(success).catch(fail);
}

export { getComment };
