package com.bitc.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitc.board.util.Criteria;
import com.bitc.comment.dao.CommentDAO;
import com.bitc.comment.vo.CommentVO;

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

		return null;
	}

	@Override
	public String deleteComment(CommentVO vo) throws Exception {

		return null;
	}

	@Override
	public List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception {

		return null;
	}

}
