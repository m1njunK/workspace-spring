package com.bitc.vailid;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
)
public class SendMailTest {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Test
	public void mailSenderTest()throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
		// 발신자
		helper.setFrom("rlaalswns0730@gmail.com");
		// 수신자
		helper.setTo("rla6085@naver.com");
		// 참조 helper.setCc(null);
		// 숨은 참조 helper.setBcc(null);
		// 첨부파일
		// helper.addAttachment(파일 이름, 전송할 파일 new File(""));
		helper.setSubject("[테스트 메일 전송 제목]");
		// true - text/html 형식으로 전송
		helper.setText("<h1>전송하고자하는 메일 내용</h1>",true);
		// 완성된 메일 메세지 전송
		// 전송이 완료되거나 실패 했을때 응답이 돌아올때 까지 blocking
		mailSender.send(message);
		
		System.out.println("발신완료");
	}
}