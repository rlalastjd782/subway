package com.board.domain.qnapost;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.common.dto.SearchDto;
import com.board.domain.post.PostRequest;

@Mapper
public interface QnaPostMapper {

	  /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(QnaPostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param idx - PK
     * @return 게시글 상세정보
     */
    QnaPostResponse findByIdx(Long idx);
    
    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(QnaPostRequest params);

    /**
     * 게시글 삭제
     * @param idx - PK
     */
    void deleteByIdx(Long idx);

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return 게시글 리스트
     */
    List<QnaPostResponse> findAll(SearchDto params);

    /**
     * 게시글 수 카운팅
     * @param params - search conditions
     * @return 게시글 수
     */
    int count(SearchDto params);
    
    int updatereviewcnt(Long idx);

	String getWriterId(PostRequest params);
	
	String getTitle(PostRequest params);

	String getContent(PostRequest params);
}
