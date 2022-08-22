package com.board.domain.analysis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.board.common.dto.SearchDto;
@Controller
public class AnalysisController {

	
	@GetMapping("/analysis/analysis")
	public String analysisPage(@ModelAttribute("params")final SearchDto params, Model model){
	model.addAttribute("analysisUrl", "/analysis/analysis");
    return "analysis/analysis";
 }
	
}
