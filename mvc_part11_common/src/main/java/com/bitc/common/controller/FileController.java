package com.bitc.common.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		return new ResponseEntity<>(FileUtils.getBytes(realPath, fileName),FileUtils.getHeaders(fileName),HttpStatus.CREATED);
	}
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) throws Exception{
		ResponseEntity<byte[]> entity = null;
		boolean isDeleted = FileUtils.deleteFile(realPath, fileName);
		return new ResponseEntity<>(isDeleted ? "SUCCESS" : "FAILED", HttpStatus.OK);
	}
	
	// 첨부된 게시글의 첨부 파일 모두 삭제
	@PostMapping("/deleteAllFiles")
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files) throws Exception{
		boolean isDeleted = false;
		for(String file : files) {
			isDeleted = FileUtils.deleteFile(realPath, file);
		}
		return new ResponseEntity<>(isDeleted ? "DELETED" : "FAILED" , HttpStatus.OK);
	}
	
}
