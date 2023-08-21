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
	
	@PostMapping("remove")
	public String remove(int bno) throws Exception{
		bs.remove(bno);
		return "redirect:/board/listReply";
	}
	
	@GetMapping("modify")
	public String modify(int bno,Model model) throws Exception{
		model.addAttribute("board",bs.readBoard(bno));
		return "board/modify";
	}
	
	@PostMapping("modify")
	public String modify(BoardVO vo,RedirectAttributes rttr) throws Exception{
		// 게시글 수정 처리
		bs.modify(vo);
		// 상세보기 페이지에 게시글 번호 파라미터 전달
		rttr.addAttribute("bno",vo.getBno());
		// 게시글 상제보기 페이지로 리다이렉트
		return "redirect:/board/read";
	}
	
	@GetMapping("replyRegister")
	public String replyRegister(int bno,Model model) throws Exception{
		// bno == 답변을 작성하려는 원본글 번호
		model.addAttribute("board",bs.readBoard(bno));
		return "board/replyRegister";
	}
	
	@PostMapping("replyRegister")
	public String replyRegister(BoardVO vo) throws Exception{
		// 답변 글 등록
		bs.replyRegister(vo);
		// 답변글 등록 완료 시 게시글 목록 페이지로 이동
		return "redirect:/board/listReply";
	}
	
}

