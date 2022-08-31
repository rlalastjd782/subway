package com.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.board.domain.analysis.AnalysisController;

@SpringBootTest
//@AutoConfigureMockMvc
public class JythonTest {

	/*
	 * @Autowired public MockMvc mockMvc;
	 */
	  // 이 부분은 Autowired 한 것과 동일하다
		/*
		 * @BeforeEach public void before() { mockMvc = MockMvcBuilders
		 * .standaloneSetup(AnalysisController.class) // 테스트 대상 Controller 를 넣어준다.
		 * .alwaysExpect(MockMvcResultMatchers.status().isOk()) // 특정 필수 조건을 지정
		 * .build(); }
		 */

	
	@Test
	//프로젝트에 포함한 파이썬 파일 실행
	public void execJython1() {
		 System.setProperty("python.import.site", "false");
		 PythonInterpreter interpreter = new PythonInterpreter();
		 interpreter.execfile("/analysis/sample.py");
 	     PyFunction function = (PyFunction)interpreter.get("callFunc",PyFunction.class); 
    	 PyObject o = function.__call__(new PyInteger(8),new PyInteger(23)); 
	}
	
	
	//내컴퓨터에 포함된 파이썬 파일 실행
	@Test
	public void execJython2() {
		Runtime rt = Runtime.getRuntime();
		String parameter = "넘어온데이터";
		String fetching = "python " + "c:\\sample.py " + "\""+parameter+ "\"";
		System.out.println("commandToExecute : "+fetching);

		try {
			System.out.println("명령문 : "+fetching);
			Process process = rt.exec(fetching);	//명령어 수행
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),"euc-kr"));
			Scanner scanner = new Scanner(br);
			scanner.useDelimiter(System.getProperty("line.separator"));
			while(scanner.hasNext())
				System.out.println(scanner.next());
		    scanner.close();
		    br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//모델 돌리는 테스트
	@Test
	public void realJython() {
		System.out.println("realJython 실행");
		Runtime rt = Runtime.getRuntime();
		String parameter = "역곡";
		
		String fetching = "python " + "c:\\myModel.py " + "\""+parameter+ "\"";
		System.out.println("commandToExecute : "+fetching);

		try {
			System.out.println("명령문 : "+fetching);	//python c:\myModel.py "역곡"
			Process process = rt.exec(fetching);	//명령어 수행
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),"euc-kr"));
			Scanner scanner = new Scanner(br);
			String result ="";
			scanner.useDelimiter(System.getProperty("line.separator"));
			System.out.println("스캐너 돌기 직전");
			while(scanner.hasNext()) {
				result = scanner.next();
				System.out.println(result);
			}
			System.out.println("최종결과 :"+ result);
		    scanner.close();
		    br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("테스트 끝");
		}
	}
	
	
	/*
	 * @Test public void realJythonWithWeb() throws Exception{ Runtime rt =
	 * Runtime.getRuntime(); String parameter = "역곡"; String result = ""; String
	 * fetching = "python " + "c:\\myModel.py " + "\""+parameter+ "\"";
	 * System.out.println("commandToExecute : "+fetching);
	 * 
	 * mockMvc.perform( MockMvcRequestBuilders .get("/") // 넣어준 컨트롤러의 Http Method 와
	 * URL 을 지정 .accept(MediaType.APPLICATION_JSON) // accept encoding 타입을 지정 )
	 * .andExpect(status().isOk()) .andExpect(
	 * content().string(equalTo("Hello World!"))); //응답 받은 데이터를 String 비교
	 * 
	 * 
	 * 
	 * try { System.out.println("명령문 : "+fetching); Process process =
	 * rt.exec(fetching); //명령어 수행 BufferedReader br = new BufferedReader(new
	 * InputStreamReader(process.getInputStream(),"euc-kr")); Scanner scanner = new
	 * Scanner(br); scanner.useDelimiter(System.getProperty("line.separator"));
	 * while(scanner.hasNext()) result = scanner.next(); scanner.close();
	 * br.close(); } catch (IOException e) { e.printStackTrace(); } }
	 */
	

	
}


