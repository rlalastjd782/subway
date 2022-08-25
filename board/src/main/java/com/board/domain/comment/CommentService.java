package com.board.domain.comment;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	@Autowired
	private  CommentMapper commentMapper;


	public boolean registerComment(CommentRequest params) {
		int queryResult = 0;

		if (params.getCIdx() == null) {
			queryResult = commentMapper.insertComment(params);
		} else {
			queryResult = commentMapper.updateComment(params);
		}

		return (queryResult == 1) ? true : false;
	}


	public boolean deleteComment(Long idx) {
		int queryResult = 0;

		CommentResponse comment = commentMapper.selectCommentDetail(idx);

		if (comment != null) {
			queryResult = commentMapper.deleteComment(idx);
		}
		
		return (queryResult == 1) ? true : false;
	}


	public List<CommentRequest> getCommentList(CommentRequest params) {
		List<CommentRequest> commentList = Collections.emptyList();

		int commentTotalCount = commentMapper.selectCommentTotalCount(params);
		if (commentTotalCount > 0) {
			commentList = commentMapper.selectCommentList(params);
		}

		return commentList;
	}


	public String getWriterId(CommentRequest params) {
		// TODO Auto-generated method stub
		
		String result = commentMapper.getWriterId(params);
		
		return result;
	}
}
