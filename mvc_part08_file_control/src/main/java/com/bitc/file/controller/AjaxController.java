package com.bitc.file.controller;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AjaxController {
	
	private final String uploadDir;
	private final ServletContext context;
	private String realPath;

	@PostConstruct
	public void initPath() {
		
		realPath = context.getRealPath(File.separator+uploadDir);
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	// uploadFiles
	@PostMapping("uploadFiles")
	public ResponseEntity<List<String>> uploadFiles(
			List<MultipartFile> files) throws Exception{
		System.out.println(files);
		return null; 
	}
	
}
