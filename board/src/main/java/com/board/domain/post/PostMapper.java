package com.board.domain.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.common.dto.SearchDto;

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(PostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param idx - PK
     * @return 게시글 상세정보
     */
    PostResponse findByIdx(Long idx);
    
    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(PostRequest params);

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
    List<PostResponse> findAll(SearchDto params);

    /**
     * 게시글 수 카운팅
     * @param params - search conditions
     * @return 게시글 수
     */
    int count(SearchDto params);
    
    int updatereviewcnt(Long idx);

    // 인덱스에 해당하는 아이디 가져오기
	String getWriterId(PostRequest params);

	// 인덱스에 해당하는 글 제목 가져오기
	String getTitle(PostRequest params);

	// 인덱스에 해당하는 글 내용 가져오기
	String getContent(PostRequest params);

}