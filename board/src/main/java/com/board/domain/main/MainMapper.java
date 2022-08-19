package com.board.domain.main;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.common.dto.SearchDto;
import com.board.domain.post.PostResponse;
import com.board.domain.qnapost.QnaPostResponse;
@Mapper

public interface MainMapper {

	
	// 큐엔에이 게시판 가져ㅛ오기
	List<QnaPostResponse> QnaFindAll(SearchDto params);
	
	
	// 자유게시판 글 목록 가져오기
	List<PostResponse> findAll(SearchDto params);
	
	
}
