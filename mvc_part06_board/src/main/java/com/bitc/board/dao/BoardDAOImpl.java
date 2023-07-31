package com.bitc.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bitc.board.util.Criteria;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Repository("bd")
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

	private final SqlSession session;
	
	@Override
	public int create(BoardVO vo) throws Exception {
		int result = session.insert("boardMapper.create",vo);
		return result;
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = session.selectOne("boardMapper.read",bno);
		return board;
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		int result = session.update("boardMapper.update",vo);
		return result;
	}

	@Override
	public int delete(int bno) throws Exception {
		int result = session.delete("boardMapper.delete",bno);
		return result;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		session.update("boardMapper.updateCnt",bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> list = session.selectList("boardMapper.boardList");
		return list;
	}

	@Override
	public int totalCount() throws Exception {
		int cnt = session.selectOne("boardMapper.total");
		return cnt;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		Map<String,Integer> map = new HashMap<>();
		map.put("startRow", cri.getStartRow());
		map.put("perPageNum",cri.getPerPageNum());
		List<BoardVO> list = session.selectList("boardMapper.criList",map);
		return list;
	}

}
