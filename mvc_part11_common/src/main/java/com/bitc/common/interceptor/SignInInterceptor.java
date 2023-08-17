package com.bitc.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.user.vo.LoginDTO;
import com.bitc.user.vo.UserVO;

public class SignInInterceptor implements HandlerInterceptor {
	// preHandle -- 전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
		}
		return true;
	}
	
	/*
	@PostMapping("/signInPost")
	public ModelAndView signInPost(LoginDTO dto, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		UserVO vo = us.signIn(dto);
		session.setAttribute("userInfo", vo);
		mav.setViewName("redirect:/");
		return mav;
	}
	*/
	
	// postHandle -- 후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO) session.getAttribute("userInfo");
		System.out.println("interceptor user info : " + userInfo);
		
		ModelMap modelObj = modelAndView.getModelMap();
		LoginDTO dto = (LoginDTO) modelObj.get("loginDTO");
		
		if(userInfo != null) {
			// 자동 로그인 요청 처리
			if(dto.isUserCookie()) {
				Cookie cookie = new Cookie("signInCookie",userInfo.getUid());
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*15);
				response.addCookie(cookie);
			}
		}else {
			// 로그인 실패
			String message = "로그인 실패";
			modelAndView.addObject("message",message);
			modelAndView.setViewName("/user/signIn");
		}
	}

}
