package com.ssafy.vue.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("BoardController V1")
@RestController
@RequestMapping("/board")
//@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	
//    @ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
//	@GetMapping
//	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
//		logger.debug("retrieveBoard - 호출");
//		return new ResponseEntity<List<Board>>(boardService.retrieveBoard(), HttpStatus.OK);
//	}
    
    @ApiOperation(value = "게시판 글목록(limit)", notes = "특정 범위의 게시글의 정보를 반환한다.", response = List.class)
   	@GetMapping("/list/{pageno}/{condition}/{text}")
   	public ResponseEntity<List<Board>> retrieveBoardLimitOffset(@PathVariable String condition, @PathVariable String text, @PathVariable int pageno) throws Exception {
   		logger.debug("retrieveBoardLimitOffset - 호출");
   		System.out.printf("%s%n",condition);
   		return new ResponseEntity<List<Board>>(boardService.retrieveBoardLimitOffset(10, (pageno-1)*10, condition, text), HttpStatus.OK);
   	}
    
    @ApiOperation(value = "게시글의 전체 개수", notes = "게시글의 전체 개수를 반환한다.", response = List.class)
   	@GetMapping("/count/{condition}/{text}")
   	public ResponseEntity<Integer> selectBoardTotalCount(@PathVariable String condition, @PathVariable String text) throws Exception {
   		logger.debug("selectBoardTotalCount - 호출");
   		return new ResponseEntity<Integer>(boardService.selectBoardTotalCount(condition, text), HttpStatus.OK);
   	}

    @ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<Board> detailBoard(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<Board>(boardService.detailBoard(articleno), HttpStatus.OK);
	}

    @ApiOperation(value = "게시판 글등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Board board) {
		logger.debug("writeBoard - 호출");
		if (boardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "게시판 글정보 수정", notes = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		logger.debug("updateBoard - 호출");
		
		if (boardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (boardService.deleteBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}