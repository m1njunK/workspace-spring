package com.bitc.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bitc.board.util.Criteria;
import com.bitc.comment.vo.CommentVO;

public interface CommentDAO {
	
	/**
	 * 전체 댓글 목록
	 * @param 댓글이 저장된 게시글 번호
	 * @return 해당 게시글의 전체 게시글 목록
	 */
	
	@Select("SELECT * FROM tbl_comment WHERE bno = #{bno} ORDER BY cno DESC")
	List<CommentVO> commentList(int bno)throws Exception;
	
	/**
	 * @param 삽입될 댓글 정보
	 * @return - 삽입된 행의 개수
	 */
	@Insert("INSERT INTO tbl_comment(bno,commentText,commentAuth) VALUES(#{bno},#{commentText},#{commentAuth})")
	int insert(CommentVO vo) throws Exception;
	
	/**
	 * @param  수정할 댓글 정보
	 * @return  수정된 행의 개수
	 */
	int update(CommentVO vo) throws Exception;
	
	/**
	 * @param 삭제할 댓글 번호
	 * @return 삭제된 행의 개수
	 */
	int delete(int cno) throws Exception;

	/**
	 * @param bno - 검색할 게시글 번호
	 * @param cri - 요청한 페이지에 따른 페이징 정보
	 * @return - 페이징 처리된 게시글 목록
	 * @throws Exception
	 */
	
	List<CommentVO> listPage(int bno, Criteria cri) throws Exception;
	
	/**
	 * @param bno 검색할 게시글 번호
	 * @return 해당 게시글에 작성된 댓글 개수
	 */
	
	int totalCount(int bno) throws Exception;
	
}








