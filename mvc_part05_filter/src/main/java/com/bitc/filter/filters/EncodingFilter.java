package com.bitc.filter.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(" init EncodingFilter Start ");
		
		System.out.println(" init EncodingFilter End ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(" doFilter EncodingFilter Start ");
		// 전처리
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		// 후처리
		System.out.println(" doFilter EncodingFilter End ");
	}

	@Override
	public void destroy() {
		System.out.println(" EncodingFilter Destroy ");
	}
}
