package com.board.domain.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long cIdx;             // PK
    private Long bIdx;    
    private String cContent;      // 내용
    private String cWriter;   
	
}
