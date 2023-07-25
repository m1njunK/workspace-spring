package com.bitc.di.controller.second;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc.di.dao.TestDAO;
import com.bitc.di.service.TestService;

@Controller
public class SecondController {
	
	@Resource(name="path")
	private String path;
	
	
	//@Inject
	@Autowired(required=false)
	TestService ts;
	
	@Inject
	@Named("td")
	TestDAO dao;
	
	// 객체가 생성이 되고
	// 의존성 주입이 완료 되고 나면 
	// bean으로 등록되기 전에 최초에 한번 호출되는 method
	@PostConstruct
	public void init() {
		System.out.println(ts);
		System.out.println(path);
		System.out.println(dao);
	}
	
	@GetMapping("main")
	public void doMain() {
		System.out.println("Second controller do main 호출");
		if(dao != null) {
			dao.select("Second Controller ");
		}else {
			System.out.println("Second Controller dao is null");
		}
		
		if(ts != null){
			ts.testService("SecondController ");
		}else {
			System.out.println("Second Controller ts is null");
		}
		
	}
	
}













