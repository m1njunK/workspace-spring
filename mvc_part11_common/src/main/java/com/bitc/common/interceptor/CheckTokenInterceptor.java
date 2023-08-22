package com.bitc.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class CheckTokenInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CSRF 인증 토큰 확인");
		System.out.println("Check Token Interceptor");
		if(request.getMethod().toUpperCase().equals("POST")) {
			HttpSession session = request.getSession();
			String sessionToken = (String) request.getAttribute("csrf_token");
			
			String requestToken = request.getParameter("csrf_token");
			
			if(requestToken == null || requestToken.equals("") || !requestToken.equals(sessionToken)) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.println("<scrip>");
				pw.println("alert('잘못된 접근 입니다.');");
				pw.println("history.go(-1);");
				pw.println("</script>");
				return false;
			}
		}
		return true;
	}
	
}
