package com.board.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MailDTO {
	
	// 메일 주소
	private String email;
	
	// 메일 제목
    private String title;
    
    // 메일 내용
    private String content;
    
    // 랜덤으로 생성된 인증 번호
    private String code;
    
    // 유저에게 입력받은 인증 번호
    private String certify;
}