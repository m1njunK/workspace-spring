package com.bitc.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bitc.board.provider.BoardQueryProvider;
import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.SearchCriteria;

public interface BoardMapper {
	/*
	@Insert("INSERT INTO re_tbl_board (title,content,writer,uno)VALUES(#{title},#{content},#{writer},#{uno})")
	*/
	@InsertProvider(type=BoardQueryProvider.class, method = "registTest")
	void register(BoardVO board)throws Exception;
	
	@Update("UPDATE re_tbl_board SET origin = LAST_INSERT_ID() WHERE bno = LAST_INSERT_ID()")
	void updateOrigin() throws Exception;
	
	// 검색 기능이 추가된 리스트
	/*
	@Select("SELECT * FROM re_tbl_board "
			+ "<if test='#{searchType} == \"t\"'> "
			+ " WHERE TITLE LIKE concat('%',#{keyword},'%')"
			+ "</if>"
			+ "limit #{startRow},#{perPageNum}")
	*/
	@SelectProvider(type=BoardQueryProvider.class, method = "searchSelcetSql")
	List<BoardVO> listCriteria(SearchCriteria cri) throws Exception;

	/**
	 * Search 된 결과의 게시물이 몇개인지 검색
	 * SELECT count(*) FROM re_tbl_board 
	 * WHERE TITLE LIKE CONCAT('%',#{keyword},'%')
	 */
	@SelectProvider(type=BoardQueryProvider.class, method="searchSelectCount")
	int listReplyCount(SearchCriteria cri) throws Exception;
	
	/**
	 * 상세보기 게시글 조회수 증가
	 */
	@UpdateProvider(type=BoardQueryProvider.class, method="updateCnt")
	void updateCnt(int bno) throws Exception;
	
	/**
	 * 게시글 상세 정보
	 */
	@Select("SELECT * FROM re_tbl_board WHERE bno = #{bno}")
	BoardVO readBoard(int bno) throws Exception;

	/**
	 * 게시글 삭제 요청 처리
	 */
	@Update("UPDATE re_tbl_board SET showboard = 'n', updatedate = now() WHERE bno = #{bno}")
	void remove(int bno) throws Exception;

	/**
	 * 게시글 정보 수정
	 */
	@Update("UPDATE re_tbl_board SET title = #{title}, content = #{content}, writer = #{writer} WHERE bno = #{bno}")
	void modify(BoardVO vo) throws Exception;

	@Update("UPDATE re_tbl_board SET seq = seq + 1 WHERE origin = #{origin} AND seq> #{seq}")
	void updateReply(BoardVO vo) throws Exception;

	/**
	 * 원본글을 제외한 답글들의 정렬값 수정
	 */
	@Insert("INSERT INTO re_tbl_board(title,content,writer,origin,depth,seq,uno) VALUES(#{title},#{content},#{writer},#{origin},#{depth},#{seq},#{uno})")
	void replyRegister(BoardVO vo) throws Exception;
}
