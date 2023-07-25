package com.bitc.di.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc.di.dao.TestDAO;
import com.bitc.di.service.TestService;
import com.bitc.di.vo.TestBoardVO;

/**
 *	 DI Annotation (Dependency Injection) - 의존성 주입
 *	 spring이 관리하는 bean을 우리가 원하는 필드에 주입받아 사용하게 해주는 Annotation
 *												- 1.8까지 기본으로 지원
 *	 		@Autowired 		@Qualifier		 @Inject			@Resource      
 * 범용성		 스프링 전용		 스프링 전용		자바에서 지원		    자바에서 지원
 * 연결성    타입에 맞춰서 주입   bean의 name을    타입에 맞춰서 주입     	이름으로 먼저 검색 후 
 * 						   이용하여 주입	 @Named 를 이용해서        없으면 타입으로 검색
 *  * 					   독립적 사용 x      name으로 주입 가능		
 */

@Controller
public class HomeController {


	/*
	 * @Autowired
	 * 
	 * @Resource(name="path") private String path;
	 */

	@Autowired
	@Qualifier(value="path")
	private String pathImage;
	
	@Autowired
	private int size;
	
	@Autowired(required = false)
	TestService ts; // new TestServiceImpl();

	@Autowired
	TestBoardVO vo;
	
	@Autowired
	@Qualifier("td")
	TestDAO testDao;
	
	@GetMapping("/")
	public String home() {
		System.out.println("path : " + pathImage);
		System.out.println("size : " + size);
		
		System.out.println("home test board vo : " + vo);
		
		if(ts != null) {
			ts.testService("Home Controller");
		}else {
			System.out.println("Home ts is null");
		}
		
		if(testDao != null) {
			System.out.println(testDao);
			testDao.select("HomeController");
		}else {
			System.out.println("HomeController testDao is null");
		}
		
		return "home";
	}
	
}






