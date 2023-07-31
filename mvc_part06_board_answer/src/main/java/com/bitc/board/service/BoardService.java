package com.bitc.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

public interface BoardService{
	
	// 게시글 작성 - 성공 유무에 따라 메세지 전달
	String regist(BoardVO board)throws Exception;
	
	// 조회수 증가
	void updateCnt(int bno)throws Exception;
	
	// 게시글 상세보기
	BoardVO read(int bno) throws Exception;
	
	// 게시글 전체 목록 페이지
	List<BoardVO> listAll() throws Exception;
	
	// 게시글 수정 - 성공 유무에 따라 메세지 전달
	String modify(BoardVO board) throws Exception;
	
	// 게시글 삭제 - 성공 유무에 따라 메세지 전달
	String remove(int bno) throws Exception;
	
	// 페이징 처리된 리스트 목록
	List<BoardVO> listCriteria(Criteria cri)throws Exception;
	
	// 페이징 정보 처리
	PageMaker getPageMaker(Criteria cri)throws Exception;

	
}











