package com.bitc.common.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bitc.common.util.FileUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {
	
	private final ServletContext context;
	
	private final String uploadPath;
	
	private String realPath;
	
	// 의존성 주입이 완료되면 최초에 한번 실행함 @PostConstruct
	@PostConstruct
	public void initPath() {
		realPath = context.getRealPath(File.separator+uploadPath);
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	
	@PostMapping("/uploadFile")
	public ResponseEntity<Object> uploadFile(
			MultipartFile[] file
			){
		ResponseEntity<Object> entity = null;
		try {
			List<String> list = new ArrayList<>();
			for(MultipartFile f : file) {
					String savedName = FileUtils.uploadFile(realPath, f);
					list.add(savedName);
			}
			entity = new ResponseEntity<>(list,HttpStatus.CREATED);
		} catch (Exception e) {
			HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.add("Content-Type", "text/plain;charset=utf-8");
			entity = new ResponseEntity<>(e.getMessage(),headers,HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
