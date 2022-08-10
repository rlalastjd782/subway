package com.board.domain.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CommentMapper {


	public int insertComment(CommentRequest params);

	public CommentRequest selectCommentDetail(Long idx);

	public int updateComment(CommentRequest params);

	public int deleteComment(Long idx);

	public List<CommentRequest> selectCommentList(CommentRequest params);

	public int selectCommentTotalCount(CommentRequest params);

	

	
}
