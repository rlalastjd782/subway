#테이블생성

CREATE TABLE `users` (
    `id`            varchar(16)   NOT NULL COMMENT '아이디, 기본키',
    `admin_yn`      tinyint(1)    NOT NULL default '0' COMMENT '관리자 여부',
    `pw`            varchar(16)   NOT NULL COMMENT '비밀번호',
    `name`          varchar(10)   NOT NULL COMMENT '이름',
    `gender`        varchar(1)    NOT NULL COMMENT '성별',
    `nickname`      varchar(16)   NOT NULL unique COMMENT '닉네임',
    `email`         varchar(40)   NOT NULL unique COMMENT '이메일',
    PRIMARY KEY (`id`)
);

CREATE TABLE `free_board` (
   `idx`   INT(10)   NOT null AUTO_INCREMENT  unique COMMENT 'pk글번호',
   `writerid`    varchar(16)   NOT null COMMENT '작성자ID',
   `title`   VARCHAR(100)   NOT null COMMENT '제목',
   `content`   VARCHAR(2000)   NOT null COMMENT '내용',
   `writer`   VARCHAR(16)   NOT null COMMENT '작성자',
   `view_cnt`   INT(10)   NOT null COMMENT '조회수',
   `notice_yn`   tinyint(1)   NOT NULL   COMMENT '공지글여부',
   `del_yn`   tinyint(1)   NOT null COMMENT '삭제여부',
   `write_date`   datetime   NOT null COMMENT '작성일자',
   `modified_date`   datetime   null COMMENT '수정일자'
);

CREATE TABLE `fb_comment` (
    `c_idx` INT(10) NOT null  auto_increment primary key COMMENT '댓글번호',
    `c_content` VARCHAR(200)    NOT NULL    COMMENT '댓글내용',
    `c_writer`  VARCHAR(16) NOT NULL    COMMENT '작성자',
    `c_writerid`    varchar(16)   NOT null COMMENT '작성자ID',
    `c_date`    datetime    NOT NULL    COMMENT '등록일',
    `del_yn`    tinyint(1)  NOT NULL    COMMENT '삭제여부',
    `b_idx` INT(10) NOT NULL    COMMENT '게시글번호',
    `c_modidate`    datetime    NULL    COMMENT '수정일자',
    `c_deldate` datetime    NULL    COMMENT '삭제일'
    
);

ALTER TABLE `fb_comment` ADD CONSTRAINT `FK_free_board_TO_fb_comment_1` FOREIGN KEY (
    `b_idx`
)
REFERENCES `free_board` (
    `idx`
);

CREATE TABLE `pro_sol_board` (
    `idx`   INT(10) NOT null AUTO_INCREMENT  unique primary KEY,
    `writerid`    varchar(16)   NOT null COMMENT '작성자ID',
    `title` VARCHAR(100)    NOT NULL,
    `head_title`    VARCHAR(10) not NULL,
    `content`   VARCHAR(2000)   NOT NULL,
    `writer`    VARCHAR(16) NOT NULL,
    `view_cnt`  INT(10) NOT NULL,
    `notice_yn` tinyint(1)  NULL,
    `del_yn`    tinyint(1)  NOT NULL,
    `write_date`    datetime    NOT NULL,
    `modified_date` datetime    NULL
);

CREATE TABLE `psb_comment` (
  `c_idx` int NOT NULL AUTO_INCREMENT COMMENT '댓글번호',
  `c_content` varchar(200) NOT NULL COMMENT '댓글내용',
  `c_writer` varchar(16) NOT NULL COMMENT '작성자',
  `c_writerid`    varchar(16)   NOT null COMMENT '작성자ID',
  `c_date` datetime NOT NULL COMMENT '등록일',
  `del_yn` tinyint(1) NOT NULL COMMENT '삭제여부',
  `b_idx` int NOT NULL COMMENT '게시글번호',
  `c_modidate` datetime DEFAULT NULL COMMENT '수정일자',
  `c_deldate` datetime DEFAULT NULL COMMENT '삭제일자',
  PRIMARY KEY (`c_idx`),
  KEY `FK_pro_sol_board_TO_psb_comment_1` (`b_idx`),
  CONSTRAINT `FK_pro_sol_board_TO_psb_comment_1` FOREIGN KEY (`b_idx`) REFERENCES `pro_sol_board` (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


#admin 계정 생성

insert into users
values('admin', 1, '1q2w3e4r!', '관리자이름', 'M', '관리자닉네임', 'admin@asd.asd');


#테이블 drop

drop table users;
drop table free_board ;
drop table fb_comment;
drop table pro_sol_board;
drop table psb_comment;