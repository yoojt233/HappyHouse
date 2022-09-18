package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 댓글의 정보", description = "댓글의 상세 정보를 나타낸다.")
public class CommentDto {

	@ApiModelProperty(value = "게시글 고유번호")
	private String comment_no;
	@ApiModelProperty(value = "도서 고유번호", example = "111-111-1111")
	private String articleno;
	@ApiModelProperty(value = "댓글 작성자")
	private String user_name;
	@ApiModelProperty(value = "댓글내용")
	private String comment;
	@ApiModelProperty(value = "작성 시간")
	private String comment_time;

	public String getComment_no() {
		return comment_no;
	}

	public void setComment_no(String comment_no) {
		this.comment_no = comment_no;
	}

	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public CommentDto(String comment_no, String articleno, String user_name, String comment, String comment_time) {
		this.comment_no = comment_no;
		this.articleno = articleno;
		this.user_name = user_name;
		this.comment = comment;
		this.comment_time = comment_time;
	}

	public CommentDto() {
	}

}
