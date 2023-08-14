package com.bitc.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("HomeController home call");
		return "home";
	}
	
	@GetMapping("test1")
	public String test1() {
		System.out.println("HomeController test1 요청");
		
		System.out.println("HomeController test1 요청 처리 완료");
		return "home";
	}
	
	@GetMapping("test2")
	public String test2(Model model) {
		System.out.println("HomeController test2 요청");
		model.addAttribute("result","test2 job");
		System.out.println("HomeController test2 요청 처리 완료");
		return "home";
	}
	
	@GetMapping("test3")
	public String test3() {
		System.out.println("HomeController test3 요청");
		System.out.println("HomeController test3 요청 처리 완료");
		return "result";
	}
	
}
















