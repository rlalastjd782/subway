package com.board.domain.qnapost;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
@Getter
public class QnaPostResponse {

    private Long idx;                       // PK
    private String writerid; // 작성자 ID
    private String title;                  // 제목
    private String headTitle;			//말머리
    private String content;                // 내용
    private String writer;                 // 작성자
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean delYn;   				// 삭제 여부
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime writeDate;     // 작성일자
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;    // 수정일자
}
