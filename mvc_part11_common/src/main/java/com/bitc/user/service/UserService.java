package com.bitc.user.service;

import com.bitc.user.vo.LoginDTO;
import com.bitc.user.vo.UserVO;

public interface UserService {
	/**
	 * 회원가입 
	 */
	void signUp(UserVO vo) throws Exception;
	
	/**
	 * SignIn 요청 처리
	 */
	UserVO signIn(LoginDTO dto) throws Exception;
	
	/**
	 * 아이디로 사용자 정보 검색
	 */
	UserVO getUserById(String uid) throws Exception;
}
