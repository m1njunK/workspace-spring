package com.bitc.mvc.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.mvc.vo.TestVO;

public class TestInterceptor implements HandlerInterceptor{

	@Autowired(required = false)
	TestVO vo;
	
	/**
	 * Servlet 이 Controller mapping method를 호출하기 전 실행
	 * @return mapping method 호출 여부 - true 호출, false 미호출
	 */
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		System.out.println("TestInterceptor preHandler START ====");
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		System.out.println("controller : " + method.getBean());
		System.out.println("methodObj : " + methodObj);
		
		String command = request.getRequestURI().substring(request.getContextPath().length()+1);
		System.out.println(command);
		if(command.equals("test1")) {
			response.sendRedirect("test2");
			System.out.println("TestInterceptor preHandler return false");
			return false;
		}
		
		System.out.println("TestInterceptor preHandler END ====");
		return true;
	}

	/**
	 * Controller에 mapping method가 호출 완료 되고 난 후
	 * servlet으로 반환 값이 전달 되기 전에 호출
	 */
	
	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("TestInterceptor postHandle START ===");
		System.out.println(modelAndView);
		Map<String, Object> map = modelAndView.getModel();
		for(String key : map.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		String viewName = modelAndView.getViewName();
		System.out.println(viewName);
		if(viewName.equals("result")) {
			modelAndView.setViewName("home");
		}
		
		Object result = map.get("result");
		
		if(result == null) {
			modelAndView.addObject("result","postHandle job");
		}
		String result1 = (String) request.getAttribute("result1");
		System.out.println("Post Handle result1 : " + result1);
		
		System.out.println("TestInterceptor postHandle START ===");
	}
	
	/**
	 * response 응답 정보가 filter로 전달되기 전 호출
	 * servlet의 응답(출력)이 완료되고 난 후 호출
	 */

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("TestInterceptor afterCompletion START ===");
		System.out.println(response.getContentType());
		System.out.println(request.getAttribute("result"));
		System.out.println(request.getAttribute("result1"));
		System.out.println("TestInterceptor afterCompletion END ===");
	}
	
}
