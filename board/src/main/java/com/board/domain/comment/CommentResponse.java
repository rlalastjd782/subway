package com.board.domain.comment;

import lombok.Getter;
@Getter

public class CommentResponse {

  	private Long cIdx;             // PK
  	private Long bIdx;  
    private String cContent;      // 내용
    private String cWriter;    //작성자
    private Boolean delYn;     // 삭제여부
    private GsonLocalDateTime cDate;  // 작성날짜
    private GsonLocalDateTime cModidate; // 수정날짜
    private GsonLocalDateTime cDeldate; // 삭제날짜
	
}
