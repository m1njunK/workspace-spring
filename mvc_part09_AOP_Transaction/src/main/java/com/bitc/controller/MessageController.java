package com.bitc.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitc.service.MessageService;
import com.bitc.vo.MessageVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
	
	private final MessageService ms;
	
	// messages/adds
	@PostMapping("/add")
	public ResponseEntity<String> addMessage(MessageVO vo){
		ResponseEntity<String> entity = null;

		try {
			System.out.println("MessageController : " + vo);
			ms.addMessage(vo);
			entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			entity = new ResponseEntity<>(e.getMessage(),header,HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<MessageVO>> readlist(){
		ResponseEntity<List<MessageVO>> entity = null;
		try {
			List<MessageVO> list = ms.list();
			entity = new ResponseEntity<List<MessageVO>>(list,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<MessageVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PatchMapping("/read/{mno}/{uid}")
	public ResponseEntity<Object> readMessage(
				@PathVariable(name="mno") int mno,
				@PathVariable(name="uid") String uid
			){
		ResponseEntity<Object> entity = null;
		
		try {
			MessageVO vo = ms.readMessage(uid, mno);
			entity = new ResponseEntity<>(vo,HttpStatus.OK);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			entity = new ResponseEntity<>(e.getMessage(),headers,HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
