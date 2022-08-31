package com.board.domain.post;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.common.dto.MessageDto;
import com.board.common.dto.SearchDto;
import com.board.paging.PagingResponse;
import com.board.users.dto.UserRequestDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

    
	private final PostService postService;

	
	
	// 게시글 작성 페이지
    @GetMapping("/post/write")
    public String openPostWrite(@RequestParam(value = "idx", required = false) final Long idx, Model model) {
        if (idx != null) {
            PostResponse post = postService.findPostByIdx(idx);
            model.addAttribute("post", post);
        }
        return "post/write";
    }
    
    
    
 // 신규 게시글 생성
    @PostMapping("/post/save")
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    
 // 게시글 리스트 페이지
    @GetMapping("/post/list")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
        PagingResponse<PostResponse> response = postService.findAllPost(params);
        model.addAttribute("response", response);
        model.addAttribute("listUrl", "/post/list");
        return "post/list";
    }
    
 // 게시글 상세 페이지
    @GetMapping("/post/view")
    public String openPostView(@RequestParam final Long idx, Model model) throws Exception {
        postService.updatereviewcnt(idx);
    	PostResponse post = postService.findPostByIdx(idx);
        model.addAttribute("post", post);
        return "post/view";
    }
    
 // 기존 게시글 수정
    @PostMapping("/post/update")
    public String updatePost(final PostRequest params, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
    

    // 게시글 삭제
    @PostMapping("/post/delete")
    public String deletePost(@RequestParam final Long idx,
                             @RequestParam final Map<String, Object> queryParams,
                             Model model) {

        postService.deletePost(idx);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list", RequestMethod.GET, queryParams);
        return showMessageAndRedirect(message, model);
    }
    
    
    // 게시물 idx값에 해당되는 작성자 id 가져오기
 	@ResponseBody
 	@PostMapping(value = "/get/postidxid")
 	public String getidxid(PostRequest params) {
 		
 		String result = postService.getWriterId(params);
 		
 		return result;
 	}
 	
 	
 	// 게시물 idx값에 해당되는 작성자 title 가져오기
  	@ResponseBody
  	@PostMapping(value = "/get/postidxtitle")
  	public String getidxtitle(PostRequest params) {
  		
  		String result = postService.getTitle(params);
  		
  		return result;
  	}
  	
  	
  	// 게시물 idx값에 해당되는 작성자 content 가져오기
   	@ResponseBody
   	@PostMapping(value = "/get/postidxcontent")
   	public String getidxcontent(PostRequest params) {
   		
   		String result = postService.getContent(params);
   		
   		return result;
   	}
    
 // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
    
    
}