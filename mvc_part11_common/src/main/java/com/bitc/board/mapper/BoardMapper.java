package com.bitc.board.mapper;

import java.util.List;

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
}
