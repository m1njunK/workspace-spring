package com.bitc.common.session;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bitc.user.vo.UserVO;

/**
 *	HttpSessionListener - HttpSession 객체가 생성되고 제거 될때 발생되는 이벤트 감시자
 *
 * 	HttpSessionAttributeListener
 *  HttpSession 객체의 attribute(속성값)이 추가 변경 제거 되었을때 발생 하는 이벤트를
 *  전달 받아 처리하는 interface
 *
 */
public class MySessionEventListener implements HttpSessionListener, HttpSessionAttributeListener{

	public static Hashtable<String,Object> sessionRepository;
	
	static {
		sessionRepository = new Hashtable<>();
	}
	
	public static boolean expireDuplicatedSession(String uid, String sessionId) {
		System.out.println("Active Session Count : " + sessionRepository.size());
		Enumeration<Object> enumeration = sessionRepository.elements();
		while(enumeration.hasMoreElements()) {
			HttpSession session = (HttpSession) enumeration.nextElement();
			UserVO user = (UserVO) session.getAttribute("userInfo");
			// 사용자 정보가 session에 존재하고
			// 새로 로그인 할려는 uid랑 일치하는 사용자 일때
			if(user != null && user.getUid().equals(uid)) {
				if(!session.getId().equals(sessionId)) {
					System.out.printf("login - user %s, sessionId %s %n", user.getUid(),sessionId);
					// session.invalidate();
					session.setAttribute("invalidate", "중복 로그인으로 로그아웃됩니다. 싸우세요");
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded 호출");
		System.out.printf("SESSIONID : %s %n", event.getSession().getId());
		System.out.printf("SESSION attribute 추가됨 name : %s, value : %s %n", event.getName(),event.getValue());
		if(event.getName().equals("userInfo")) {
			sessionRepository.put(event.getSession().getId(), event.getSession());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("attributeRemoved 호출");
		System.out.printf("SESSIONID : %s %n", event.getSession().getId());
		System.out.printf("SESSION attribute 삭제됨 name : %s, value : %s %n", event.getName(),event.getValue());
		HttpSession session = event.getSession();
		if(event.getName().equals("userInfo")) {
			sessionRepository.remove(session.getId());
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("attributeRemoved 호출");
		System.out.printf("SESSIONID : %s %n", event.getSession().getId());
		System.out.printf("SESSION attribute 변경 name : %s, value : %s %n", event.getName(),event.getValue());
	}

	/**
	 * 사용자가 최초에 연결 요청을 전달하여 새로운 Session 객체가 생성되거나
	 * session 이 invalidate() 되어 새로운 Session이 생성되면
	 * 새로 생성된 Session 정보와 함께 was에서 호출 되는 method
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.printf("생성된 SESSIONID %s %n", se.getSession().getId());
	}

	/**
	 * HttpSession 객체가 제거 되었을 때 호출
	 * @param 제거된 session 객체 정보
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.printf("삭제된 SESSIONID %s %n", session.getId());
		sessionRepository.remove(session.getId());
	}
	
}
