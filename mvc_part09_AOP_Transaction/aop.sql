CREATE TABLE tbl_user(
	uno INT PRIMARY KEY auto_increment,
	uid VARCHAR(50) NOT NULL UNIQUE,
	upw VARCHAR(50) NOT NULL,
	uname VARCHAR(50) NOT NULL,
	upoint INT NOT NULL DEFAULT 0
);

CREATE TABLE tbl_message(
	mno INT PRIMARY KEY auto_increment,					-- 메세지 번호
	targetid VARCHAR(50) NOT NULL,						-- 수신자 아이디
	sender VARCHAR(50) NOT NULL,						-- 발신자 아이디
	message TEXT NOT NULL,								-- 발신 메세지
	opendate TIMESTAMP NULL,							-- 수신 시간 메세지 확인 시간
	senddate TIMESTAMP NOT NULL DEFAULT now(),			-- 발신 시간
	FOREIGN KEY(targetid) REFERENCES tbl_user(uid),
	FOREIGN KEY(sender) REFERENCES tbl_user(uid)
);

INSERT INTO tbl_user(uid, upw, uname)
VALUES('id001','pw001','IRON MAN');

INSERT INTO tbl_user(uid, upw, uname)
VALUES('id002','pw002','THOR');

INSERT INTO tbl_user(uid, upw, uname)
VALUES('id003','pw003','SPIDER MAN');

SELECT * FROM TBL_USER;

SELECT * FROM TBL_MESSAGE;
