package com.bitc.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitc.board.service.BoardService;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor 
@Log
public class BoardController {
	
	//@Autowired 
	private final BoardService bs;
	
	/* "게시글 작성 페이지 요청" */
	// @RequestMapping(value="/register",method=RequestMethod.GET)
	@GetMapping("register")
	public void register()throws Exception{
		// /board/register
		// WEB-INF/views/board/register.jsp
		System.out.println("게시글 작성 페이지 요청");
	}
	
	/**
	 * 게시글 등록 요청 처리 추가
	 */
	// @PostMapping("board/register")
	@PostMapping("register")
	public String registerPost(BoardVO vo,
			// HttpSession session
			RedirectAttributes rttr
			) throws Exception{
		System.out.println("board/register POST 요청");
		String result = bs.regist(vo);
		// session.setAttribute("message", result);
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/listPage";
	}
	
	/**
	 * 게시글 상세보기 요청 처리 - 게시글 번호
	 * @throws Exception 
	 */
	// @GetMapping("board/read")
	@GetMapping("readPage")
	public String readPage(
			// model.addAttribute("cri",cri);
			@ModelAttribute("cri") Criteria cri,
			int bno,Model model) throws Exception {
		// 조회수 증가
		bs.updateCnt(bno);
		// 상세보기 요청 게시글 정보 
		BoardVO vo = bs.read(bno);
		model.addAttribute("boardVO",vo);
		return "/board/read";
	}

	/**
	 * 게시글 수정 페이지 요청
	 * - 게시글 수정 화면 출력 
	 */
	// @GetMapping("board/modify")
	@GetMapping("modify")
	public void modifyGet(
			int bno, Model model,Criteria cri)throws Exception{
		BoardVO vo = bs.read(bno);
		model.addAttribute(vo);
	}
	
	/**
	 * 게시글 수정 처리 요청
	 * 게시글 수정 완료 후 redirect - 수정게시글 상세보기 페이지 이동
	 */
	// @PostMapping("board/modify")
	@PostMapping("modify")
	public String modify(
			RedirectAttributes rttr,
			BoardVO vo,
			Criteria cri) throws Exception{
		String result = bs.modify(vo);
		rttr.addFlashAttribute("result",result);
		rttr.addFlashAttribute("cri",cri);
		// get 방식으로 전달해준다 ?bno=?
		rttr.addAttribute("bno",vo.getBno());
		return "redirect:/board/readPage";  // ?bno="+vo.getBno();
	}

	/**
	 * 게시글 삭제 완료 후 listPage 페이지 로 이동 - redirect 
	 */
	 // @PostMapping("board/remove")
	@PostMapping("remove")
	public String remove(int bno,Criteria cri,RedirectAttributes rttr) throws Exception{
		String result = bs.remove(bno);
		rttr.addFlashAttribute("result",result);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		return "redirect:/board/listPage"; 
	}
	
	/**
	 *  페이징 처리 된 게시글 출력 페이지
	 *  param : 요청 page, perPageNum 
	 * @throws Exception 
	 */
	// board/listPage
	// @GetMapping("board/listPage")
	@GetMapping("listPage")
	// board/listPage.jsp 페이지에서 출력 하기 위해 필요한 Model
	// 사용자가 요청 페이지 정보 
	public void listPage(Criteria cri, Model model) throws Exception {
		List<BoardVO> list = bs.listCriteria(cri);
		model.addAttribute("list",list);
		// 페이징 블럭 정보 전달
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("pm",pm);
	}

	
}















