package com.bitc.comment.service;

import java.util.List;

import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.comment.vo.CommentVO;

public interface CommentService {
	/**
	 * 게시글 번호를 전달 받아서
	 * bno 값이 일치하는 전체 댓글 리스트
	 */
	List<CommentVO> commentList(int bno)throws Exception;
	
	/**
	 * 댓글 삽입
	 */
	String addComment(CommentVO vo) throws Exception;
	
	/**
	 * 댓글 수정
	 */
	String updateComment(CommentVO vo) throws Exception;
	
	/**
	 * 댓글 삭제
	 */
	String deleteComment(int cno) throws Exception;
	
	/**
	 * 페이징 처리된 댓글 리스트 
	 */
	List<CommentVO> commentListPage(
			int bno, Criteria cri
			) throws Exception;
	
	PageMaker getPageMaker(int bno, Criteria cri) throws Exception;
}
