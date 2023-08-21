package com.bitc.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bitc.board.mapper.AttachmentMapper;
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
	private final AttachmentMapper attachMapper;
	
	@Transactional
	@Override
	public void regist(BoardVO vo) throws Exception {
		// 원본글 등록
		mapper.register(vo);
		// origin column 값을 등록된 게시글 번호로 수정
		mapper.updateOrigin(); 
		
		// 첨부된 파일 이름 리스트
		List<String> files = vo.getFiles();
		if(files != null) {
			for(String fullName : files) {
				attachMapper.addAttach(fullName);
			}
		}
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
		BoardVO vo = mapper.readBoard(bno);
		// 첨부파일 목록 추가
		List<String> fileList = attachMapper.getAttach(bno);
		vo.setFiles(fileList);
		return vo;
	}

	@Override
	public void replyRegister(BoardVO vo) throws Exception {
		// origin, depth, seq 
		// 답변을 달려는 원본 글 보다 아래쪽에 배치된 글들
		mapper.updateReply(vo);
		
		vo.setDepth(vo.getDepth() + 1);
		vo.setSeq(vo.getSeq() + 1);
		
		mapper.replyRegister(vo);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		// re_tbl_board 정보 수정
		// title, content, writer
		mapper.modify(vo);
		
		// 기존에 등록 되어 있던 첨부파일 정보 삭제
		attachMapper.deleteAttach(vo.getBno());
		
		List<String> fileList = vo.getFiles();
		if(fileList != null && !fileList.isEmpty()) {
			// 수정 시 등롥된 첨부파일 존재
			for(String fullName : fileList) {
				attachMapper.replaceAttach(vo.getBno(),fullName);
			}
		}
	}

	@Override
	public void remove(int bno) throws Exception {
		// 첨부파일 db 삭제
		attachMapper.deleteAttach(bno);
		// 게시글 삭제
		mapper.remove(bno);
	}

	@Override
	public List<String> getAttach(int bno) throws Exception {

		return null;
	}

}
