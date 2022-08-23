package com.board.domain.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
	
	@Autowired
	private MainMapper mainMapper;
	
    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<QnaPostResponse> QnaFindAllPost(final SearchDto params) {
        int count = mainMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<QnaPostResponse> list = mainMapper.QnaFindFive(params);
        return new PagingResponse<>(list, pagination);
    }
    
    /* 게시글 리스트 조회
    * @param params - search conditions
    * @return list & pagination information
    */
    
   public PagingResponse<PostResponse> findAllPost(final SearchDto params) {
       int count = mainMapper.count(params);
       Pagination pagination = new Pagination(count, params);
       params.setPagination(pagination);

       List<PostResponse> list = mainMapper.findFive(params);
       return new PagingResponse<>(list, pagination);
   }
   
   
   
   //뉴스 크롤링하기
	private static String naverNews = "https://search.naver.com/search.naver?where=news&query=지하철";
	

	public List<MainResponse> getNaverNews() throws IOException {

	 	
	List<MainResponse> mainResponseList = new ArrayList<>();	
    Document doc = Jsoup.connect(naverNews).get();
    Elements contents = doc.select(".news_area");

    
    for(Element content : contents) {
    	MainResponse mainResponse =  MainResponse.builder()
    			.newsPress(content.select(".press").text())
    			.newsTitle(content.select(".news_tit").text())
    			.newsDetail(content.select(".news_dsc").text())
    			.newsLink(content.select(".news_dsc a").attr("href"))
    			.newsinfo(content.select("span").text())
    			.build();
    			
    	mainResponseList.add(mainResponse);
    }
    
    return mainResponseList;
    
    
    

   
   
	}
    
	
    
    
    
}
