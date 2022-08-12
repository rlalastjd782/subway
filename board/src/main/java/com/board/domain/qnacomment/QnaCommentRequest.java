package com.board.domain.qnacomment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaCommentRequest {

    private Long cIdx;             // PK
    private Long bIdx;  
    private String cContent;      // 내용
    private String cWriter;
	
	
}
