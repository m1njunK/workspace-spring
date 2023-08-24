CREATE TABLE validation_member(
	u_no INT PRIMARY KEY auto_increment,		-- 회원번호
	u_id VARCHAR(100) NOT NULL UNIQUE,			-- 회원아이디(email)
	u_pw VARCHAR(100) NOT NULL,					-- 비밀번호
	u_profile VARCHAR(300) NULL,				-- 프로필 이미지
	u_phone VARCHAR(20) NOT NULL,				-- 전화번호
	u_birth VARCHAR(20) NOT NULL,				-- 생년월일(19960730)
	u_addr VARCHAR(50),							-- 주소
	u_addr_detail VARCHAR(200),					-- 상세주소
	u_addr_post VARCHAR(50),					-- 우편번호
	u_point INT DEFAULT 0,						-- 포인트 
	u_info char(1) DEFAULT 'y',					-- 개인 정보 이용 동의
	u_date TIMESTAMP NOT NULL DEFAULT now(),	-- 계정 생성일
	u_visi_date TIMESTAMP NOT NULL DEFAULT now(),	-- 최종 방문일(마지막 로그인)
	u_withdraw char(1) DEFAULT 'n'					-- 회원정보 숨김(탈퇴)
);