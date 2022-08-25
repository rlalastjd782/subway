package com.board.domain.qnacomment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.comment.CommentRequest;

@Mapper
public interface QnaCommentMapper {

	
	
	public int insertComment(QnaCommentRequest params);

	public QnaCommentResponse selectCommentDetail(Long Idx);

	public int updateComment(QnaCommentRequest params);

	public int deleteComment(Long idx);

	public List<QnaCommentRequest> selectCommentList(QnaCommentRequest params);

	public int selectCommentTotalCount(QnaCommentRequest params);

	public String getWriterId(CommentRequest params);
}
