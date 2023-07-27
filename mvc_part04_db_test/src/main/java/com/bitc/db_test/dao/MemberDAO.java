package com.bitc.db_test.dao;

import java.util.List;

import com.bitc.db_test.vo.MemberVO;

public interface MemberDAO {
	
	/**
	 * @param member - 등록될 회원 정보
	 * @return - 삽입된 회원 행 개수
	 */
	int insertMember(MemberVO member);

	/**
	 * @param userid - 회원 아이디로 회원 정보 검색
	 * @return - 검색된 회원 정보
	 */
	MemberVO readMember(String userid);
	
	/**
	 * @param userid
	 * @param userpw
	 * @return 아이디와 패스워드가 일치하는 회원 정보
	 */
	MemberVO readMemberWithPass(String userid, String userpw);
	
	/**
	 * @return - 전체 회원 목록
	 */
	List<MemberVO> readmemberList();
	
	/**
	 * @return - 마지막에 등록된 회원 번호 - 가장 높은 번호
	 */
	Integer readMax();
	
}
