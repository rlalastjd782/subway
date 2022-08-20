package com.board.domain.qnapost;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.common.dto.MessageDto;
import com.board.common.dto.SearchDto;
import com.board.domain.post.PostRequest;
import com.board.domain.post.PostResponse;
import com.board.domain.post.PostService;
import com.board.paging.PagingResponse;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class QnaPostController {

    
	private final QnaPostService qnaPostService;

	// 게시글 작성 페이지
    @GetMapping("/post/qnawrite")
    public String openPostWrite(@RequestParam(value = "idx", required = false) final Long idx, Model model) {
        if (idx != null) {
            QnaPostResponse post = qnaPostService.findPostByIdx(idx);
            model.addAttribute("post", post);
        }
        return "post/qnawrite";
    }
    
    
    
 // 신규 게시글 생성
    @PostMapping("/post/qnasave")
    public String savePost(final QnaPostRequest params, Model model) {
        qnaPostService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/qnalist.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    
 // 게시글 리스트 페이지
    @GetMapping("/post/qnalist")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
        PagingResponse<QnaPostResponse> response = qnaPostService.findAllPost(params);
        model.addAttribute("response", response);
        return "post/qnalist";
    }
    
 // 게시글 상세 페이지
    @GetMapping("/post/qnaview")
    public String openPostView(@RequestParam final Long idx, Model model) throws Exception {
        qnaPostService.updatereviewcnt(idx);
    	QnaPostResponse post = qnaPostService.findPostByIdx(idx);
        model.addAttribute("post", post);
        return "post/qnaview";
    }
    
 // 기존 게시글 수정
    @PostMapping("/post/qnaupdate")
    public String updatePost(final QnaPostRequest params, Model model) {
        qnaPostService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/qnalist.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
    

    // 게시글 삭제
    @PostMapping("/post/qnadelete")
    public String deletePost(@RequestParam final Long idx,
                             @RequestParam final Map<String, Object> queryParams,
                             Model model) {

        qnaPostService.deletePost(idx);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/qnalist.do", RequestMethod.GET, queryParams);
        return showMessageAndRedirect(message, model);
    }
    
 // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
    
}
