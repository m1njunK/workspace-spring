package com.bitc.board.service;

import java.util.List;

import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.PageMaker;
import com.bitc.common.util.SearchCriteria;

public interface BoardService {
	
	// 원본 게시글 등록
	void regist(BoardVO vo) throws Exception;
	
	// 게시글 목록
	List<BoardVO> listCriteria(SearchCriteria cri) throws Exception;
	
	// 페이징 블럭
	PageMaker getPageMaker(SearchCriteria cri) throws Exception;
	
	// 조회수 증가
	void updateCnt(int bno) throws Exception;
	
	// 게시글 상세 정보
	BoardVO readBoard(int bno) throws Exception;
	
	// 답변글 등록
	void replyRegister(BoardVO vo) throws Exception;
	
	// 게시글 수정
	void modify(BoardVO vo) throws Exception;
	
	// 게시글 삭제 처리
	void remove(int bno) throws Exception;
	
	// 첨부파일 목록
	List<String> getAttach(int bno) throws Exception;
	
	
}
