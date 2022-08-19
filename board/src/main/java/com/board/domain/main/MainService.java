package com.board.domain.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.common.dto.SearchDto;
import com.board.domain.post.PostMapper;
import com.board.domain.post.PostResponse;
import com.board.domain.qnapost.QnaPostMapper;
import com.board.domain.qnapost.QnaPostResponse;
import com.board.paging.Pagination;
import com.board.paging.PagingResponse;

@Service

public class MainService {
    
	
	@Autowired
	private QnaPostMapper qnaPostMapper;
	
	@Autowired
	private PostMapper postMapper;
	
    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<QnaPostResponse> QnaFindAllPost(final SearchDto params) {
        int count = qnaPostMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<QnaPostResponse> list = qnaPostMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }
    
    /* 게시글 리스트 조회
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
   
    
    
    
    
    
}
