package com.bitc.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * tbl_attach table 작업 수행
 */
public interface AttachmentMapper {

	/**
	 * 첨부파일 등록
	 */
	@Insert("INSERT INTO tbl_attach(fullName, bno) VALUES (#{fullName}, LAST_INSERT_ID())")
	void addAttach(String fullName)throws Exception;
	
	/**
	 * 첨부파일 목록 검색
	 */
	@Select("SELECT fullName FROM tbl_attach WHERE bno = #{bno}")
	List<String> getAttach(int bno) throws Exception;

	/**
	 * 테이블에 등록된 첨부파일 목록 삭제
	 */
	@Delete("DELETE FROM tbl_attach WHERE bno = #{bno}")
	void deleteAttach(int bno) throws Exception;

	/**
	 * 첨부파일 목록 갱신
	 */
	@Insert("INSERT INTO tbl_attach(fullName, bno) VALUES(#{fullName}, #{bno})")
	void replaceAttach(@Param("bno") int bno,@Param("fullName") String fullName) throws Exception;
}