package com.bitc.filter.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter{

	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(" init EncodingFilter Start ");
		encoding = filterConfig.getInitParameter("encoding");
		System.out.println(" init EncodingFilter End : " + encoding);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(" doFilter EncodingFilter Start ");
		// 전처리
		HttpServletRequest req = (HttpServletRequest) request;
		String method = req.getMethod();
		
		if(method.toUpperCase().equals("POST")) {
			request.setCharacterEncoding(encoding);
		}
		// FilterChain에 등록된 다른 Filter의 doFilter 호출
		// FilterChain의 모든 doFilter method가 호출 되면 Servlet으로 요청 전달 후 결과 확인
		chain.doFilter(request, response);
		// 후처리
		System.out.println(" doFilter EncodingFilter End ");
	}

	@Override
	public void destroy() {
		System.out.println(" EncodingFilter Destroy ");
	}
}
