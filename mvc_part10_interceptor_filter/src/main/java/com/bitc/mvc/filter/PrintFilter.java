package com.bitc.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitc.mvc.vo.TestVO;

@WebFilter(
	urlPatterns = "/*",
	initParams = @WebInitParam(name="filterParam", value="UTF-8")
)
public class PrintFilter implements Filter{
	
	@Autowired(required = false)
	TestVO vo;
	
	private String filterParam;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("PrintFilter 초기화 시작 -- ");
		filterParam = filterConfig.getInitParameter("filterParam");
		System.out.println("filterParam : " + filterParam);
		System.out.println("PrintFilter 초기화 종료 -- ");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("PrintFilter doFilter 시작 -- ");
		System.out.println("doFilter vo : " + vo);
		request.setCharacterEncoding(filterParam);
		chain.doFilter(request, response);
		System.out.println("PrintFilter doFilter 종료 -- ");
	}

	@Override
	public void destroy() {
		System.out.println("PrintFilter destroy()");
	}

	
	
}










