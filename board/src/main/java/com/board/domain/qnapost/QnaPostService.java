package com.board.domain.qnapost;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.board.common.dto.SearchDto;
import com.board.domain.post.PostRequest;
import com.board.domain.post.PostResponse;
import com.board.paging.Pagination;
import com.board.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaPostService {
    private final QnaPostMapper qnaPostMapper;

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final QnaPostRequest params) {
    	System.out.println(params.getHeadTitle());
    	qnaPostMapper.save(params);
        return params.getIdx();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public QnaPostResponse findPostByIdx(final Long idx) {
        return qnaPostMapper.findByIdx(idx);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final QnaPostRequest params) {
        qnaPostMapper.update(params);
        return params.getIdx();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePost(final Long idx) {
    	qnaPostMapper.deleteByIdx(idx);
        return idx;
    }

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<QnaPostResponse> findAllPost(final SearchDto params) {
        int count = qnaPostMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<QnaPostResponse> list = qnaPostMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }
    
    //조회수 증가
    public int updatereviewcnt(Long idx) throws Exception {
    	return qnaPostMapper.updatereviewcnt(idx);
    
}
}
