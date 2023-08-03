package com.bitc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitc.board.service.BoardService;
import com.bitc.board.service.UploadService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/image/*")
@RequiredArgsConstructor 
public class UploadController {
	
	private final UploadService us;
	
	@GetMapping("upload")
	public void upload(){};
	
	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		return us.upload(file,request);
	}
	
	@GetMapping("list")
	public void imgList(){};
	
}
