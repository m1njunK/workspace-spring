package com.bitc.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.bitc.user.mapper.UserMapper;
import com.bitc.user.vo.UserVO;

public class CheckCookieInterceptor implements HandlerInterceptor {
	
	@Autowired
	UserMapper mapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CheckCookie 시작");
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) {
			System.out.println("이미 로그인한 사용자");
			return true;
		}
		
		Cookie cookie = WebUtils.getCookie(request, "signInCookie");
		if(cookie != null) {
			// cookie.getValue() == uid
			String uid = cookie.getValue();
			UserVO userInfo = mapper.getUserById(uid);
			if(userInfo != null) {
				session.setAttribute("userInfo", userInfo);
			}
		}
		System.out.println("CheckCookie 종료");
		return true;
	}

}
