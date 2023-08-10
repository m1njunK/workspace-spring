package com.bitc.file.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 요청에 대한 처리를 할 class
 * upload, download, delete
 */
public class FileUtils {
	
	public static String uploadFile(String realPath,MultipartFile file) throws Exception{
		
		String uploadFileName = "";
		// 동일 디렉토리에 동일한 이름의 파일 중복을 최소화
		UUID uid = UUID.randomUUID();
		String originalName = file.getOriginalFilename();
		String savedName = uid.toString().replace("-", "");
		
		savedName += "_"+(originalName.replace("_", " "));
		System.out.println(savedName);
		// URL encoding으로 변환된 파일 이름일 경우 공백을 + 로 치환하여 전달되기 때문에
		// + 기호를 공백으로 치환
		savedName = savedName.replace("+", " ");
		// 해당되는 파일이 업로드 되는 날짜를 기준으로 디렉토리를 생성하여 저장
		// \2023\08\10\
		String datePath = calcPath(realPath);
		File f = new File(realPath+datePath,savedName);
		file.transferTo(f);
		// 원본 파일 업로드 완료
		
		// 업로드 된 파일의 확장자
		// xxxxxxxxx.(jpg)
		// 업로드된 파일이 이미지 파일인지 일반 파일인지 확인하기 위하여 
		// 파일 확장자를 확인
		// image/*
		// JPG, JPEG, PNG, GIF
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		System.out.println(formatName);
		if(MediaUtils.getMediaType(formatName) != null) {
			// 이미지 파일 - Thumbnail 이미지 경로 반환
			uploadFileName = makeThumbnail(realPath,datePath,savedName,formatName);
		}else {
			// 일반 파일
			uploadFileName = makePathName(datePath, savedName);
		}
		return uploadFileName;
	}

	private static String makeThumbnail(
		String realPath, String datePath, String savedName, String ext) throws IOException {
		String name = "";
		// 원본이미지 정보
		File file = new File(realPath+datePath,savedName);
		// ImageScalr 는 BufferedImage 타입으로 이미지를 제어
		// ImageIO javax package에 ImageIO class 는
		// Image 타입의 파일을 쉽게 읽고 쓸수 있도록 read, writer method를 제공 하는 class
		BufferedImage image = ImageIO.read(file);
		
		// scalr 객체를 이용해서 원본이미지를 복제한 Thumbnail 이미지 생성
		BufferedImage sourceImage = Scalr.resize(
				image,						// 원본 이미지 
				Scalr.Method.AUTOMATIC,		// 고정크기에따른 상대 크기
				Scalr.Mode.FIT_TO_HEIGHT,	// 고정 위치
				100							// 크기
		);
		
		String thumbnailImage = realPath+datePath+File.separator+"s_"+savedName;
		// ImageIO.write(출력할 이미지 데이터, 확장자, 출력 위치);
		ImageIO.write(sourceImage, ext, new File(thumbnailImage));
		name = thumbnailImage.substring(realPath.length()).replace(File.separatorChar, '/');
		System.out.println(name);
		return name;
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
	
	// 지정된 경로의 파일 이름을 가지고 전달할 파일 정보를 byte[]로 반환
	public static byte[] getBytes(String realPath,String fileName) throws Exception{
		
		File file = new File(realPath,fileName);
		InputStream is = new FileInputStream(file);
		
		/*
		long length = file.length();
		// 바이트 수
		length = is.available();
		byte[] bytes = new byte[(int)length];
		for(int i=0; i < bytes.length; i++) {
			bytes[i] = (byte)is.read();
		}
		is.close();
		return bytes;
		*/
		byte[] bytes = IOUtils.toByteArray(is);
		IOUtils.close(is);
		
		return bytes;
	}
	
	// 전달된 파일 정보로 브라우저가 파일 종류에 사오간없이
	// 다운로드를 받아야될 파일이라고 인식할 수 있도록 headers 정보 추가
	public static HttpHeaders getOctetHeaders(String fileName) throws Exception{
		HttpHeaders headers = new HttpHeaders();
		// applicaiton/octet-stream
		// octet 8비트/ 1byte 단위의 이진 데이터가 전송 됨을 의미함.
		// 해석 할 수 없는 파일로 브라우저가 해석하여 다운로드 하게 됨.
		// headers.setContentType(new MediaType("application","octet-stream"));
		// headers.add("Content-Type", "applicaiton/octet-stream");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		fileName = fileName.substring(fileName.lastIndexOf("_")+1);
	
		// Http 응답에서 Content-Disposition(배치 조치) 응답 헤더는
		// 컨텐츠가 브라우저에 인라인으로 표시되어야 되는지 
		// 웹페이지 일부인지 또는 첨부파일인지 여부를 나타내는 헤더
		// attachment : 부착, 첨부물
		/*
		fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		headers.add("content-disposition", "attachment;fileName=\""+fileName+"\"");
		*/
		ContentDisposition cd = ContentDisposition.attachment()
								.filename(fileName,Charset.forName("UTF-8"))
								.build();
		headers.setContentDisposition(cd);
		return headers;
	}
	
	public static HttpHeaders getHeaders(String fileName) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType m = MediaUtils.getMediaType(ext);
		
		if(m != null) {
			headers.setContentType(m);
		}else {
			headers = getOctetHeaders(fileName);
		}
		return headers;
	}
	
	public static boolean deleteFile(String realPath,String fileName) throws Exception {
		boolean isDeleted = false;
		// /2023/08/10/s_ddf4a5ea4cd94a8b9b41d1f598f73071_testImg.jpg
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		fileName = fileName.replace('/', File.separatorChar);
		
		System.out.println(fileName);
		
		File file = new File(realPath,fileName);
		isDeleted = file.delete();
		
		if(isDeleted && MediaUtils.getMediaType(ext) != null) {
			// s_
			fileName = fileName.replace("s_","");
			isDeleted = new File(realPath,fileName).delete();
		}
		
		return isDeleted;
	}
}
