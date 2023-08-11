package com.bitc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bitc.vo.MessageVO;

public interface MessageDAO {
	
	/**
	 * @param vo - 메세지 등록
	 */
	@Insert("INSERT INTO tbl_message(targetid,sender,message) VALUES(#{targetid},#{sender},#{message})")
	void create(MessageVO vo) throws Exception;
	
	/**
	 * @param mno - 메세지 번호로 메세지 수신확인
	 */
	void updateMessage(int mno) throws Exception;
	
	/**
	 * @param mno - 확인할 메세지 번호
	 * @return - mno가 일치하는 하나의 메세지 정보 반환
	 */
	MessageVO readMessage(int mno) throws Exception;

	/**
	 * @return - 전체 메세지 목록 반환
	 */
	@Select("SELECT * FROM tbl_message ORDER BY mno DESC")
	List<MessageVO> list() throws Exception;
}
