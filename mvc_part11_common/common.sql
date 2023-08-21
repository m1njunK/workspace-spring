show tables;

SELECT * FROM tbl_user;

CREATE TABLE re_tbl_board(
	bno INT PRIMARY KEY auto_increment,			-- 게시글 번호
	title VARCHAR(200) NOT NULL,				-- 제목
	content TEXT NOT NULL,						-- 내용
	writer VARCHAR(50) NOT NULL,				-- 작성자 이름
	origin INT NULL DEFAULT 0,					-- 원본글 그룹 번호
	depth INT NULL DEFAULT 0,					-- view 깊이 번호
	seq	INT NULL DEFAULT 0,						-- 답변글 정렬 순서
	regdate TIMESTAMP NULL DEFAULT NOW(), 		-- 게시글 등록 시간
	updatedate TIMESTAMP NULL DEFAULT now(),	-- 게시글 수정 시간
	viewcnt INT NULL DEFAULT 0,					-- 조회 수
	showboard VARCHAR(10) NULL DEFAULT 'y',		-- 게시글 삭제요청 여부
	uno INT NOT NULL,							-- 게시글 작성자 회원번호
	CONSTRAINT fk_re_tbl_uno
	FOREIGN KEY(uno) REFERENCES tbl_user(uno)
);

SELECT * FROM re_tbl_board;

commit;

-- t w c
SELECT * FROM re_tbl_board 
-- WHERE title LIKE CONCAT('%','ㅎ','%');
-- WHERE writer LIKE CONCAT('%','man','%');
-- WHERE content LIKE CONCAT('%','야옹','%');
-- tcw
WHERE CONTENT LIKE ('%','야옹','%')
OR TITLE LIKE CONCAT('%','ㅎ','%')
OR writer LIKE CONCAT('%','man','%');

DESC re_tbl_board;

-- 첨부파일 목록 저장
-- /yyyy/MM/dd/s_hi_file.png
CREATE TABLE tbl_attach(
	fullName VARCHAR(300) NOT NULL,
	bno INT NOT NULL,
	regdate TIMESTAMP NULL DEFAULT NOW(),
	CONSTRAINT fb_tbl_attch FOREIGN KEY(bno)
	REFERENCES re_tbl_board(bno)
);

commit;

select * from tbl_attach