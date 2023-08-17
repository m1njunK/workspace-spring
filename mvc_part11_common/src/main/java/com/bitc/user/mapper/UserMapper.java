package com.bitc.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bitc.user.vo.LoginDTO;
import com.bitc.user.vo.UserVO;

/**
 * 사용자 정보 처리용 MyBatis Mapper Class
 */
public interface UserMapper {
	/**
	 * 회원가입 
	 */
	@Insert("INSERT INTO tbl_user(uid,upw,uname) VALUES(#{uid},#{upw},#{uname})")
	void signUp(UserVO vo) throws Exception;
	
	/**
	 * SignIn 요청 처리
	 */
	@Select("SELECT * FROM tbl_user WHERE uid = #{uid} AND upw= #{upw}")
	UserVO signIn(LoginDTO dto) throws Exception;
	
	/**
	 * 아이디로 사용자 정보 검색
	 */
	@Select("SELECT * FROM tbl_user WHERE uid = #{uid}")
	UserVO getUserById(String uid) throws Exception;
}
