package com.board.domain.post;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long idx;                       // PK
    private String title;                  // 제목
    private String content;                // 내용
    private String writer;                 // 작성자
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean delYn;                // 삭제 여부
    private LocalDateTime writeDate;     // 작성일자
    private LocalDateTime modifiedDate;    // 수정일자  
    

}