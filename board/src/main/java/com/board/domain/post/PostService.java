package com.board.domain.post;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.board.common.dto.SearchDto;
import com.board.paging.Pagination;
import com.board.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final PostRequest params) {
        postMapper.save(params);
        return params.getIdx();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PostResponse findPostByIdx(final Long idx) {
        return postMapper.findByIdx(idx);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final PostRequest params) {
        postMapper.update(params);
        return params.getIdx();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePost(final Long idx) {
        postMapper.deleteByIdx(idx);
        return idx;
    }

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<PostResponse> findAllPost(final SearchDto params) {
        int count = postMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<PostResponse> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }
    
    //조회수 증가
    public int updatereviewcnt(Long idx) throws Exception {
    	return postMapper.updatereviewcnt(idx);
    }

	public String getWriterId(PostRequest params) {
		// TODO Auto-generated method stub
		
		String result = postMapper.getWriterId(params);
		
		return result;
	}

	public String getTitle(PostRequest params) {
		// TODO Auto-generated method stub
		
		String result = postMapper.getTitle(params);
		
		return result;
	}

	public String getContent(PostRequest params) {
		// TODO Auto-generated method stub
		
		String result = postMapper.getContent(params);
		
		return result;
	};

}