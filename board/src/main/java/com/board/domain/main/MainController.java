package com.board.domain.main;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.board.common.dto.SearchDto;
import com.board.domain.post.PostResponse;
import com.board.domain.post.PostService;
import com.board.domain.qnapost.QnaPostResponse;
import com.board.domain.qnapost.QnaPostService;
import com.board.paging.PagingResponse;

@Controller
public class MainController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private QnaPostService qnaPostService;
	
	@Autowired
	private MainService mainService;
//	
//    @GetMapping("/post/main")
//    public String home(){
//        return "post/main";
//    }
    
    
    // 게시글 리스트 페이지
    @GetMapping("/post/main")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) throws IOException {
        PagingResponse<QnaPostResponse> response = qnaPostService.findAllPost(params);
        PagingResponse<PostResponse> response2 = postService.findAllPost(params);
		List<MainResponse> mainResponse = mainService.getNaverNews();
		//@ModelAttribute("params") final SearchDto params에는  model.addAttribute("params", params); 문장이 포함된것
        model.addAttribute("response", response);
        model.addAttribute("response2", response2);
		model.addAttribute("mainResponse" , mainResponse);
		model.addAttribute("mainUrl", "/post/main");
        return "post/main";
    }
    
    
    


    

    
    
}
