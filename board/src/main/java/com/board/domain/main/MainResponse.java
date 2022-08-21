package com.board.domain.main;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class MainResponse {
	private String newsPress; // 언론사
	private String newsTitle;
	private String newsDetail;
	private String newsLink;
	
}
