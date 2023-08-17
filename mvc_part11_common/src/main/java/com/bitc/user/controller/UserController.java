package com.bitc.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitc.user.service.UserService;
import com.bitc.user.vo.LoginDTO;
import com.bitc.user.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

	private final UserService us;
	
	@RequestMapping("/signIn")
	public String singIn() {
		return "/user/signIn";
	}
	
	@GetMapping("/signUp")
	public String signUp() {
		return "/user/signUp";
	}
	
	@PostMapping("/signUpPost")
	public String signUpPost(UserVO vo,RedirectAttributes rttr) throws Exception{
		us.signUp(vo);
		rttr.addFlashAttribute("message","회원가입 성공");
		return "redirect:/user/signIn";
	}
	
	@PostMapping("/signInPost")
	public ModelAndView signInPost(LoginDTO dto, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		UserVO vo = us.signIn(dto);
		session.setAttribute("userInfo", vo);
		mav.setViewName("redirect:/");
		return mav;
	}
}
