package com.bitc.di.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc.di.dao.TestDAO;
import com.bitc.di.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Controller("main")
@RequiredArgsConstructor
public class MainController {
	
	private final TestService ts;
	private final TestDAO td;

	// public MainController() {}
	
	// @Autowired
	/*
	public MainController(TestService ts, TestDAO td) {
		this.ts = ts;
		this.td = td;
	}
	*/
	
	@GetMapping("main")
	public String main() {
		System.out.println(ts);
		System.out.println(td);
		return "home";
	}
}
