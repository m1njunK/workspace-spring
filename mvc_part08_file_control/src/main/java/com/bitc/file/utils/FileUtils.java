package com.bitc.file.utils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 요청에 대한 처리를 할 class
 * upload, download, delete
 */
public class FileUtils {
	
	public static String uploadFile(String realPath,MultipartFile file) throws Exception{
		
		String uploadFileName = "";
		
		UUID uid = UUID.randomUUID();
		String originalName = file.getOriginalFilename();
		String savedName = uid.toString().replace("-", "");
		
		savedName += "_"+(originalName.replace("_", " "));
		System.out.println(savedName);
		// URL encoding으로 변환된 파일 이름일 경우 공백을 + 로 치환하여 전달되기 때문에
		// + 기호를 공백으로 치환
		savedName = savedName.replace("+", " ");
		String datePath = calcPath(realPath);
		
		File f = new File(realPath+datePath,savedName);
		file.transferTo(f);

		uploadFileName = makePathName(datePath, savedName);
		return uploadFileName;
	}

	// URL 경로로 변경하여 문자열 path 반환
	private static String makePathName(String datePath, String savedName) {
		// /yyyy/MM/dd/savedName
		String fileName = datePath+File.separator+savedName;
		fileName = fileName.replace(File.separatorChar, '/');
		return fileName;
	}
	
	public static String calcPath(String realPath) {
		// \yyyy\MM\dd
		// /yyyy/MM/dd
		String pattern = File.separator+"yyyy"+File.separator+"MM"+File.separator+"dd";
		LocalDate date = LocalDate.now();
		String datePath = date.format(
			DateTimeFormatter.ofPattern(pattern)
		);
		File file = new File(realPath,datePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println(datePath);
		return datePath;
	}
	
}
