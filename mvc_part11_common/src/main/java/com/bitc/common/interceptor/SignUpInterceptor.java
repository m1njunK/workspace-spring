package com.bitc.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitc.user.mapper.UserMapper;
import com.bitc.user.vo.UserVO;

/**
 * 회원 가입 요청 처리 전
 * id or password 검증
 */
public class SignUpInterceptor implements HandlerInterceptor {

	@Autowired
	UserMapper mapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Sign Up Post Interceptor");
		
		String upw = request.getParameter("upw");
		String repw = request.getParameter("rePw");
	
		RequestDispatcher rd = request.getRequestDispatcher(
			"/WEB-INF/views/user/signUp.jsp"	
		);
		String message = "";
		if(!upw.equals(repw)) {
			message = "비밀번호가 일치하지 않은듯";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		
		// 중복 아이디 체크
		String uid = request.getParameter("uid");
		System.out.println("uid : " + uid);
		UserVO userVO = mapper.getUserById(uid);
		if(userVO != null) {
			message = uid+"는 이미 사용중인 아이디노";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		
		return true;
	}
}
