package com.board.domain.comment;

import java.time.LocalDateTime;

import lombok.Getter;
@Getter
public class CommentResponse {

  	private Long cIdx;             // PK
  	private Long bIdx;  
    private String cContent;      // 내용
    private String cWriter;    //작성자
    private Boolean delYn;     // 삭제여부
    private LocalDateTime cDate;  // 작성날짜
    private LocalDateTime cModidate; // 수정날짜
    private LocalDateTime cDeldate; // 삭제날짜
	
}
