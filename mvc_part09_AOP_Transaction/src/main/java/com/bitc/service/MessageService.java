package com.bitc.service;

import java.util.List;

import com.bitc.vo.MessageVO;

public interface MessageService {
	
	/**
	 * @param vo - 등록할 메세지 정보
	 * 수신자, 발신자 uid / 메세지
	 */
	void addMessage(MessageVO vo)throws Exception;
	
	/**
	 * @return - 등록 된 전체 메세지 목록
	 */
	List<MessageVO> list()throws Exception;
	
	/**
	 * @param uid - 수신자 uid
	 * @param mno - 수신 확인 할 메세지
	 * @return - 수신된 메세지 정보(opendate 확인 시간 포함)
	 */
	MessageVO readMessage(String uid, int mno)throws Exception;
	
}
