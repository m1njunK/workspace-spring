package com.bitc.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitc.board.service.BoardService;
import com.bitc.board.vo.BoardVO;
import com.bitc.user.vo.UserVO;

public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	BoardService bs;
	
	// 전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AuthInterceptor preHandler START");
		// 전체 URL 요청 경로
		String requestURI = request.getRequestURI();
		// 프로젝트 경로
		String contextPath = request.getContextPath();
		// 요청 사용자 session 정보
		HttpSession session = request.getSession();
		// session에 저장된 로그인된 사용자 정보
		Object obj = session.getAttribute("userInfo");
		
		if(obj == null) {
			// 미로그인 사용자
			response.sendRedirect(contextPath+"/user/signIn");
		}else {
			// 로그인된 사용자
			if(requestURI.equals(contextPath+"/board/register")) {
				return true;
			}
			String num = request.getParameter("bno");
			
			// 게시글 번호 존재
			if(num != null && !num.equals("")) {

				int bno = Integer.parseInt(num);
				
				// 답글 작성 요청 시에는 로그인된 사용자 이면 가능
				if(requestURI.equals(contextPath+"/board/replyRegister")) {
					System.out.println("답글 작성");
					return true;
				}
				
				// 게시글 번호로 게시글 정보 검색
				BoardVO vo = bs.readBoard(bno);
				
				UserVO userInfo = (UserVO) obj;
				
				if(userInfo.getUno() == vo.getUno()) {
					// 수정 삭제 권한이 있는 사용자
					return true;
				}else {
					response.sendRedirect(contextPath+"/board/read?bno="+bno);
					return false;
				}
			}
		}
		System.out.println("AuthIntercceptor preHandler END");
		return true;
	}
		
}
