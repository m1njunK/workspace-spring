package com.bitc.board.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private int origin;
	private int depth;
	private int seq;
	private Date regdate;
	private Date updatedate;
	private int viewcnt;
	private String showboard;
	private int uno;
	
	// 첨부된 파일 이름 리스트
	private List<String> files;
	
	// 해당 게시글에 추가된 댓글 개수
	private int commentCnt;
	
}
