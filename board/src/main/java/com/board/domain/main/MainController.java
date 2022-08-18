package com.board.domain.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/post/main")
    public String home(){
        return "post/main";
    }
}
