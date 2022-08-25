package com.board.domain.qnapost;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaPostRequest {

    private Long idx;             // PK
    private String writerid; // 작성자 ID
    private String title;        // 제목
    private String headTitle;			//말머리
    private String content;      // 내용
    private String writer;       // 작성자
    private Boolean noticeYn;    // 공지글 여부
    private int viewCnt;                   // 조회 수
    private Boolean delYn;                // 삭제 여부
    private LocalDateTime writeDate;     // 작성일자
    private LocalDateTime modifiedDate;    // 수정일자
      
    
    
    
    
    
}
