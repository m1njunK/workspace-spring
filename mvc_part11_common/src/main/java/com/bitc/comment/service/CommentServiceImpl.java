package com.bitc.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitc.comment.mapper.CommentDAO;
import com.bitc.comment.vo.CommentVO;
import com.bitc.common.util.Criteria;
import com.bitc.common.util.PageMaker;

import lombok.RequiredArgsConstructor;

// Service Spring Bean 등록
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	// CommentDAO 의존성 주입
	private final CommentDAO dao;
	
	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		List<CommentVO> list = dao.commentList(bno);
		return list;
	}

	private String getResult(int result) {
		return result == 1 ? "SUCCESS" : "FAILED";
	}
	
	@Override
	public String addComment(CommentVO vo) throws Exception {
		int result = dao.insert(vo);
		return getResult(result);
	}

	@Override
	public String updateComment(CommentVO vo) throws Exception {
		return getResult(dao.update(vo));
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		return getResult(dao.delete(cno));
	}

	@Override
	public List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}
	
	@Override
	public PageMaker getPageMaker(int bno, Criteria cri) throws Exception {
		int totalCount = dao.totalCount(bno);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setDisplayPageNum(5);
		pageMaker.setTotalCount(totalCount);
		return pageMaker;
	}

}
