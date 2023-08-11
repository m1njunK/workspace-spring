package com.bitc.dao;

import org.apache.ibatis.annotations.Update;

import com.bitc.vo.UserVO;

public interface UserDAO {
	/**
	 * @param vo - 추가할 포인트 점수 & 수정할 사용자 uid
	 */
	@Update("UPDATE tbl_user SET upoint = upoint + #{upoint} WHERE uid = #{uid}")
	void updatePoint(UserVO vo) throws Exception;
	
	/**
	 * @param uid 검색할 사용자 id
	 */
	UserVO checkUser(String uid) throws Exception;
}
