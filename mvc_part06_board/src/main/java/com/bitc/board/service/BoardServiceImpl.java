package com.bitc.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitc.board.dao.BoardDAO;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service("bs")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO bd;
		
	@Override
	public String regist(BoardVO board) throws Exception {
		String message = null;
		int result = bd.create(board);
		if(result > 0) {
			message = "<script>alert('게시글 작성 완료');location.href='listPage';</script>";
					/*"redirect:/board/listPage";*/
		}
		return message;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		bd.updateCnt(bno);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = bd.read(bno);
		return board;
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> list = bd.listAll(); 
		return list;
	}

	@Override
	public String modify(BoardVO board) throws Exception {
		int result = bd.update(board);
		if(result > 0) {
			return "redirect:/board/read?bno="+board.getBno();
		}
		return null;
	}

	@Override
	public String remove(int bno) throws Exception {
		int result = bd.delete(bno);
		if(result > 0) {
			return "redirect:/board/listPage";
		}
		return null;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = bd.listCriteria(cri);
		return list;
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		PageMaker pm = new PageMaker(cri,bd.totalCount());
		return pm;
	}

}
