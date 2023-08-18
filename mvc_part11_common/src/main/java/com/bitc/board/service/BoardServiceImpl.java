package com.bitc.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bitc.board.mapper.BoardMapper;
import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.PageMaker;
import com.bitc.common.util.SearchCriteria;
import com.bitc.common.util.SearchPageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;
	
	@Transactional
	@Override
	public void regist(BoardVO vo) throws Exception {
		// 원본글 등록
		mapper.register(vo);
		// origin column 값을 등록된 게시글 번호로 수정
		mapper.updateOrigin();
	}

	@Override
	public List<BoardVO> listCriteria(SearchCriteria cri) throws Exception {
		List<BoardVO> list = mapper.listCriteria(cri);
		return list;
	}

	@Override
	public SearchPageMaker getPageMaker(SearchCriteria cri) throws Exception {
		// 전체 게시물 수 - Search 된 결과 내의 전체 게시물 수
		SearchPageMaker spm = new SearchPageMaker();
		spm.setCri(cri);
		int totalCount = mapper.listReplyCount(cri);
		spm.setTotalCount(totalCount);
		return spm;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		mapper.updateCnt(bno);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		return mapper.readBoard(bno);
	}

	@Override
	public void replyRegister(BoardVO vo) throws Exception {
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
	}

	@Override
	public void remove(int bno) throws Exception {
	}

	@Override
	public List<String> getAttach(int bno) throws Exception {

		return null;
	}

}
