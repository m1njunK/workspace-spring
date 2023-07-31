package com.bitc.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitc.board.dao.BoardDAO;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO dao;
	
	@Override
	public String regist(BoardVO board) throws Exception {
		int result = dao.create(board);
		String message = (result != 0) ? "SUCCESS" : "FAILED";
		return message;
	}

	@Override
	public void updateCnt(int bno) throws Exception {

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return null;
	}

	@Override
	public String modify(BoardVO board) throws Exception {
		return null;
	}

	@Override
	public String remove(int bno) throws Exception {
		return null;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return null;
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		return null;
	}

}
