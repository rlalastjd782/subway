package com.board;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.comment.CommentRequest;
import com.board.domain.comment.CommentService;



@SpringBootTest
class CommentTests {

	@Autowired
	CommentService commentService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void registerComments() {
		int number = 20;

		for (int i = 1; i <= number; i++) {
			CommentRequest params = new CommentRequest();
			params.setBIdx((long) 1); // 댓글을 추가할 게시글 번호
			params.setCContent(i + "번 댓글을 추가합니다!");
			params.setCWriter(i + "번 회원");
			commentService.registerComment(params);
		}

		logger.debug("댓글 " + number + "개가 등록되었습니다.");
	}

	@Test
	public void deleteComment() {
		commentService.deleteComment((long) 10); // 삭제할 댓글 번호

		getCommentList();
	}

	@Test
	public void getCommentList() {
		CommentRequest params = new CommentRequest();
		params.setBIdx((long) 1); // 댓글을 추가할 게시글 번호

		commentService.getCommentList(params);
	}

}
