package com.bitc.filter.filters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintFilter implements Filter{

	private String printDir;
	private String printFile;
	
	private PrintWriter pw;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init PrintFilter START --------------------------");
		printDir = filterConfig.getInitParameter("printDir");
		printFile = filterConfig.getInitParameter("printFile");
		ServletContext application = filterConfig.getServletContext();
		// application server project를 기준으로
		// 지정한 경로의 절대경로를 반환
		String path = application.getRealPath(printDir);
		System.out.println("path : " + path);
		File file = new File(path);
		if(!file.exists()) {
			System.out.println("폴더 생성");
			file.mkdirs();
		}
		
		if(pw == null) {
			try {
				file = new File(path, printFile);      //append  //autoflush
				pw = new PrintWriter(new FileWriter(file,true),    true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("init PrintFilter END ----------------------------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter PrintFilter START --------------------------");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		pw.println("===============================");
		pw.printf("로그 시간 : %s %n",sdf.format(new Date()));
		pw.printf("요청 IP : %s %n",req.getRemoteAddr());
		pw.printf("요청 경로 : %s %n",req.getRequestURI());
		pw.printf("전송 방식 : %s %n",req.getMethod());
		chain.doFilter(request, response);
		pw.printf("응답 코드 : %d %n",res.getStatus());
		pw.println("===============================");
		System.out.println("doFilter PrintFilter END ----------------------------");
	}

	@Override
	public void destroy() {
		System.out.println("PrintFilter destroy --------------------------");
	}
	
}
