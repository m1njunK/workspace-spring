package com.bitc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitc.board.service.BoardService;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
	/* "게시글 작성 페이지 요청" */
	// @RequestMapping(value="/register",method=RequestMethod.GET)
	@GetMapping("board/register")
	public void register()throws Exception{
		// /board/register
		// WEB-INF/views/board/register.jsp
		System.out.println("게시글 작성 페이지 요청");
	}
	
	/**
	 * 게시글 등록 요청 처리 추가
	 * @throws Exception 
	 */
	@PostMapping("board/register")
	public String registerSubmit(String title, String content, String writer, BoardVO board) throws Exception {
		String message = bs.regist(board);
		return message;
	}
	
	/**
	 * 게시글 상세보기 요청 처리 - 게시글 번호
	 * @throws Exception 
	 */
	@GetMapping("board/read")
	public void readPage (int bno,Model model) throws Exception {
		BoardVO board = bs.read(bno);
		bs.updateCnt(bno);
		model.addAttribute(board);
	}
	/**
	 * 게시글 수정 페이지 요청
	 * - 게시글 수정 화면 출력 
	 * @throws Exception 
	 */
	@GetMapping("board/modify")
	public void modify(int bno,Model model) throws Exception {
		BoardVO board = bs.read(bno);
		model.addAttribute(board);
	}

	/**
	 * 게시글 수정 처리 요청
	 * 게시글 수정 완료 후 redirect - 수정게시글 상세보기 페이지 이동
	 * @throws Exception 
	 */
	@PostMapping("board/modify")
	public String modifySubmit(int bno, String title, String content, String writer, BoardVO board) throws Exception {
		String message = bs.modify(board);
		return message;
	}

	/**
	 * 게시글 삭제 완료 후 listPage 페이지 로 이동 - redirect 
	 * @throws Exception 
	 */
	@GetMapping("board/remove")
	public String remove(int bno) throws Exception {
		
		return bs.remove(bno);
	}
	/**
	 *  페이징 처리 된 게시글 출력 페이지
	 *  param : 요청 page, perPageNum 
	 * @throws Exception 
	 */
	// board/listPage
	@GetMapping("board/listPage")
	public void listPage(Integer page, Criteria cri, Model model) throws Exception {
		List<BoardVO> list;
		if(page == null) {
			page = 1;
		}
		list = bs.listCriteria(cri);
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("list",list);
		model.addAttribute("pm",pm);
	}
	
}















