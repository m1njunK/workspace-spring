package com.bitc.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitc.board.util.Criteria;
import com.bitc.board.vo.BoardVO;

/*@Mapper*/
public interface BoardDAO{
	
	// 게시글 작성
	@Insert("INSERT INTO tbl_board (title,content,writer) VALUES(#{title},#{content},#{writer})")
	int create(BoardVO vo) throws Exception;
	
	// 게시글 상세보기
	@Select("SELECT * FROM tbl_board WHERE bno = #{bno}")
	BoardVO read(int bno)throws Exception;
	
	// 게시글 수정
	@Update("UPDATE tbl_board SET title = #{title},content=#{content},writer=#{writer} WHERE bno = #{bno}")
	int update(BoardVO vo) throws Exception;
	
	// 게시글 삭제
	@Delete("DELETE FROM tbl_board WHERE bno = #{bno}")
	int delete(int bno) throws Exception;
	
	// 조회수 증가
	@Update("UPDATE tbl_board SET viewCnt = viewCnt + 1 WHERE bno = #{bno}")
	void updateCnt(int bno) throws Exception;
	
	// 전체 게시글 목록
	@Select("SELECT * FROM tbl_board ORDER BY bno DESC")
	List<BoardVO> listAll()throws Exception;

	// 전체 게시물 개수
	@Select("SELECT count(*) FROM tbl_board")
	int totalCount() throws Exception;
	
	// 페이징 처리된 게시물 목록
	@Select("SELECT * FROM tbl_board ORDER BY bno DESC limit #{startRow},#{perPageNum}")
	List<BoardVO> listCriteria(Criteria cri)throws Exception;
	
}











