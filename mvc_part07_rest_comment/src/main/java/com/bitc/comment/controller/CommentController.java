package com.bitc.comment.controller;


import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.comment.service.CommentService;
import com.bitc.comment.vo.CommentVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService cs;
	
	@PostMapping("")
	public ResponseEntity<String> addComment(CommentVO vo) {
		System.out.println(vo);
		ResponseEntity<String> entity = null;
		// response header 설정 정보 추가 -- springframework headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(new MediaType("application","json",Charset.forName("utf-8")));
		try {
			String message = cs.addComment(vo);
			// 필수 값은 상태 코드
			entity = new ResponseEntity<>(message,headers,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),headers,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	// 전체 댓글 목록
	@GetMapping("/all/{bno}")
	@ResponseBody
	public List<CommentVO> list(@PathVariable(name="bno") int bno) throws Exception{
		List<CommentVO> list = cs.commentList(bno);
		System.out.println(list);
		return list;
	} 
	
}
