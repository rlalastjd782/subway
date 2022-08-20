package com.board.domain.analysis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AnalysisController {

	
	@GetMapping("/analysis/analysis")
	public String analysisPage(){
     return "analysis/analysis";
 }
	
}
