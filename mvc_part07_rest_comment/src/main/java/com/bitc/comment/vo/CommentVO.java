package com.bitc.comment.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {

	private int cno;				// 댓글 번호
	private int bno;				// 댓글이 작성된 게시글 번호
	private String commentText;		// 댓글 내용
	private String commentAuth;		// 댓글 작성자
	private Date regdate;			// 댓글 작성 시간
	private Date updatedate;		// 수정 시간
	
}
