package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.CommentDto;


public interface CommentService {

	List<CommentDto> list(String comment_no);

	boolean create(CommentDto commentDto);

	boolean modify(CommentDto commentDto);

	boolean delete(String comment_no);

}
