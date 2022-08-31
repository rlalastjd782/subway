package com.board.domain.qnacomment;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.comment.CommentRequest;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class QnaCommentService {

	@Autowired
	private  QnaCommentMapper qnaCommentMapper;


	public boolean registerComment(QnaCommentRequest params) {
		int queryResult = 0;

		if (params.getCIdx() == null) {
			queryResult = qnaCommentMapper.insertComment(params);
		} else {
			queryResult = qnaCommentMapper.updateComment(params);
		}

		return (queryResult == 1) ? true : false;
	}


	public boolean deleteComment(Long idx) {
		int queryResult = 0;

		QnaCommentResponse comment = qnaCommentMapper.selectCommentDetail(idx);

		if (comment != null) {
			queryResult = qnaCommentMapper.deleteComment(idx);
		}
		
		return (queryResult == 1) ? true : false;
	}


	public List<QnaCommentRequest> getCommentList(QnaCommentRequest params) {
		List<QnaCommentRequest> commentList = Collections.emptyList();

		int commentTotalCount = qnaCommentMapper.selectCommentTotalCount(params);
		if (commentTotalCount > 0) {
			commentList = qnaCommentMapper.selectCommentList(params);
		}

		return commentList;
	}


	// 댓글 작성자 ID 가져오기
	public String getWriterId(CommentRequest params) {
		// TODO Auto-generated method stub
		
		String result = qnaCommentMapper.getWriterId(params);
		
		return result;
	}
	
	
}
