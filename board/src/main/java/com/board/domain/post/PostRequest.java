package com.board.domain.post;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Long idx;             // PK
    private String writerid; // 작성자 아이디
    private String title;        // 제목
    private String content;      // 내용
    private String writer;       // 작성자
    private Boolean noticeYn;    // 공지글 여부
    
}