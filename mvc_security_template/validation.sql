DESC validation_member;


DROP TABLE validation_member;
CREATE TABLE validation_member(
	u_no INT PRIMARY KEY auto_increment,
    u_id VARCHAR(50) NOT NULL UNIQUE,
    u_pw VARCHAR(200) NOT NULL,
    u_profile VARCHAR(200) NULL,
    u_phone VARCHAR(20) NOT NULL,
    u_name VARCHAR(20) NOT NULL,
    u_birth VARCHAR(20) NOT NULL,
    u_addr VARCHAR(100),
    u_addr_detail VARCHAR(100),
	u_addr_post VARCHAR(50), 
    u_point INT default 0,
    u_info char(1) default 'y',
    u_date TIMESTAMP NOT NULL default now(),
    u_visit TIMESTAMP NOT NULL default now(),
    u_withdraw char(1) default 'n'
);

ALTER TABLE validation_member 
ADD u_name VARCHAR(20) NOT NULL AFTER u_phone; 

DESC validation_member;

DROP TABLE validation_member_auth;

-- 권한 table
CREATE TABLE validation_member_auth(
	u_id VARCHAR(50) NOT NULL,
    u_auth VARCHAR(50) NOT NULL,
    constraint fk_member_auth FOREIGN KEY(u_id) 
    REFERENCES validation_member(u_id)
);


DESC validation_member;

commit;

SELECT * FROM validation_member;

INSERT INTO validation_member_auth(u_id,u_auth) 
VALUES('rla6085@naver.com' , 'ROLE_ADMIN');

INSERT INTO validation_member_auth(u_id,u_auth) 
VALUES('rla6085@naver.com' , 'ROLE_MEMBERSHIP');












