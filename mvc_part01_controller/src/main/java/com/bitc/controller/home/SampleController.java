package com.bitc.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.vo.ProductVO;

@Controller
public class SampleController {
	
	// /WEB-INF/views/ + "doA" + ".jsp"
	@RequestMapping("doA")
	public void doA() {
		System.out.println("doA 호출");
	}
	
	@RequestMapping("doB")
	public String doB() {
		System.out.println("doB 호출");
		return "home";
	}
	
	@RequestMapping("doC")
	public String doC(HttpServletRequest request) {
		System.out.println("doC 호출");
		request.setAttribute("modelTest", "doC test");
		return "home";
	}
	
	@RequestMapping(value="doD", method=RequestMethod.GET)
	public String doD(@RequestParam(name="msg" , required = true) String message, Model model) {
		System.out.println("doD 호출 - " + message);
		model.addAttribute("msg",message);
		return "result";
	}
	
	@RequestMapping(value="doE")
	public String doE(
			@RequestParam(name="msg" , required = false, defaultValue = "empty message") String message, Model model
		) {
		model.addAttribute("msg",message);
		return "result";
	}
	
	@RequestMapping(value="doF", method=RequestMethod.POST)
	public String doF(String msg, int age, Model model) {
		model.addAttribute("msg",msg+":"+age);
		return "result";
	}
	
	@RequestMapping("productWrite")
	public String productWrite(
				String name,
				int price,
				Model model,
				ProductVO vo
			) {
		ProductVO product = new ProductVO(name,price);
		System.out.println(product);
		model.addAttribute("product",product);
		System.out.println(vo);
		// key 값이 생략되면
		// ProductVO class 이름의 첫글자만 소문자로 key 값 지정
		// key : productVO
		model.addAttribute(vo);
		return "product";
	}
	
	//@RequestMapping(value="productWriteSubmit", method=RequestMethod.POST)
	@PostMapping("productWriteSubmit")
	public ModelAndView productWriteSubmit(ModelAndView mav, ProductVO prod) {
		// mav.addObject("productVO",prod);
		mav.addObject(prod);
		mav.addObject("product",prod);
		mav.setViewName("product");
		return mav;
	}
	
	@GetMapping("redirect")
	public String redirect() {
		return "redirect:main.home";
	}
}