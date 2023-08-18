package com.bitc.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitc.board.service.BoardService;
import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.SearchCriteria;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
	@GetMapping("register")
	public String registerGet() {
		return "board/register";
	}
	
	@PostMapping("register")
	public String registerPost(BoardVO board) throws Exception {
		bs.regist(board);
		return "redirect:/board/listReply";
	}
	
	@GetMapping("listReply")
	public String listReply(SearchCriteria cri,Model model) throws Exception{
		// 게시글 목록
		model.addAttribute("list",bs.listCriteria(cri));
		// 페이징 블럭 정보
		model.addAttribute("pm",bs.getPageMaker(cri));
		return "board/listReply";
	}
	
	@GetMapping("readPage")
	public String readPage(int bno,RedirectAttributes rttr) throws Exception{
		// 조회수 증가
		bs.updateCnt(bno);
		rttr.addAttribute("bno",bno);
		// return "redirect:/board/read/?bno="+bno;
		return "redirect:/board/read";
	}
	
	@GetMapping("read")
	public String read(int bno,Model model) throws Exception{
		// view 페이지에 상세보기 게시글 정보 전달
		model.addAttribute("board",bs.readBoard(bno));
		return "board/readPage";
	}
}
