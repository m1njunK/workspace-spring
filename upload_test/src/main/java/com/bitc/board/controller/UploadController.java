package com.bitc.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.board.service.UploadService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/image/*")
@RequiredArgsConstructor 
public class UploadController {
		//업로드로 가는 메소드
		@GetMapping("/upload")
		public void form() {}
		
		@PostMapping("/upload_ok")
		public String upload(@RequestParam("file") MultipartFile file) {
			String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
			long size = file.getSize(); //파일 사이즈
			
			System.out.println("파일명 : "  + fileRealName);
			System.out.println("용량크기(byte) : " + size);
			//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
			String uploadFolder = "C:\\test\\upload";
			
			/*
			  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
			  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
			  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
			  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
			 */
			
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");
			
			String uniqueName = uuids[0];
			System.out.println("생성된 고유문자열" + uniqueName);
			System.out.println("확장자명" + fileExtension);
			
			// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
			
			File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
			File checkDir = saveFile.getParentFile();
			if(!checkDir.exists()) {
				checkDir.mkdirs();
				System.out.println("디렉토리 생성완료...");
			}
			try {
				file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "board/home";
		}
		
		@PostMapping("/upload_ok2")
		public String upload2(MultipartHttpServletRequest files) {
			
			//서버에서 저장 할 경로
			String uploadFolder = "C:\\test\\upload";
			List<MultipartFile> list = files.getFiles("files");
			for(int i = 0; i<list.size(); i++) {
				String fileRealName = list.get(i).getOriginalFilename();
				long size = list.get(i).getSize();
				
				System.out.println("파일명 :" + fileRealName);
				System.out.println("사이즈" + size);
				
				File saveFile = new File(uploadFolder + "\\" + fileRealName);
				File checkDir = saveFile.getParentFile();
				if(!checkDir.exists()) {
					checkDir.mkdirs();
					System.out.println("디렉토리 생성완료...");
				}
				try {
					list.get(i).transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			return "board/";
		}
	
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
