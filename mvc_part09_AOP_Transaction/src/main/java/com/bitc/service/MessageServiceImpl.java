package com.bitc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitc.dao.MessageDAO;
import com.bitc.dao.UserDAO;
import com.bitc.vo.MessageVO;
import com.bitc.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
	
	private final UserDAO userDAO;
	private final MessageDAO messageDAO;
	
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		System.out.println("addMessage Service 시작");
		System.out.println("addMessage Service : " + vo);
		// 발신자 포인트 증가
		UserVO uv = new UserVO();
		uv.setUid(vo.getSender());
		uv.setUpoint(10);
		userDAO.updatePoint(uv);
		// 메세지 등록
		messageDAO.create(vo);
		System.out.println("addMessage Service 종료");
	}
	@Override
	public List<MessageVO> list() throws Exception {
		return messageDAO.list();
	}
	@Override
	public MessageVO readMessage(String uid, int mno) throws Exception {
		
		return null;
	}
	
}
