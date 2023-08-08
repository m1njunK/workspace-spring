package com.bitc.file.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bitc.file.utils.FileUtils;

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
		List<String> saves = new ArrayList<>();
		for(MultipartFile f : files) {
			saves.add(FileUtils.uploadFile(realPath, f));
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application","json",Charset.forName("utf-8")));
		return new ResponseEntity<>(saves,headers,HttpStatus.CREATED); 
	}
	
}
