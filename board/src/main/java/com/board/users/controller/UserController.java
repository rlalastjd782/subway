package com.board.users.controller;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.common.dto.SearchDto;
import com.board.users.dto.MailDTO;
import com.board.users.dto.UserRequestDTO;
import com.board.users.service.MailService;
import com.board.users.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	
	// 회원가입 페이지 진입
	@GetMapping(value = "/user/signup")
    public String Signup(@ModelAttribute("params") UserRequestDTO params, @RequestParam(value = "id", required = false) String id, Model model) {
		model.addAttribute("signupLink" , "/user/signup");
        return "user/signup";
    }
	
	
	// ajax로 리턴값 그대로 넘기려면 @ResponseBody 어노테이션을 써야 함
	// id 중복 검사
	@ResponseBody
	@PostMapping(value = "/user/id-check")
	public boolean IdCheck(UserRequestDTO params) {
		
		boolean result = userService.idCompare(params);
		
		return result;
	}
	
	
	// 닉네임 중복 검사
	@ResponseBody
	@PostMapping(value = "/user/nickname-check")
	public boolean NicknameCheck(UserRequestDTO params) {
		
		boolean result = userService.nicknameCompare(params);
		
		return result;
	}
	
	// 이메일 중복 검사
	@ResponseBody
	@PostMapping(value = "/user/email-check")
	public boolean EmailCheck(UserRequestDTO params) {
		
		boolean result = userService.emailCompare(params);
		
		return result;
	}
	
	
	// 입력받은 이메일에 인증번호 전송
	@ResponseBody
	@PostMapping(value = "/user/sendcode")
	public boolean sendCertifyCode(MailDTO params) {
		
		mailService.sendCode(params);
		
		return true;
		
	}
	
	
	// 인증번호 검사
	@ResponseBody
	@PostMapping(value = "/user/certify-check")
	public boolean CertifyCheck(MailDTO params) {

		boolean result = mailService.certifyCompare(params);
		
		return result;
	}
	
	
	// 회원가입 페이지에서 입력받은 값을 DB에 넣기
	@ResponseBody
	@PostMapping(value = "/user/user-insert")
	public boolean UserInsert(UserRequestDTO params) {

		boolean result = userService.userInsert(params);
		
		return result;
	}
	
	
	// 아이디 찾기 페이지 진입
	@GetMapping(value = "/user/findid")
	public String FindID() {

		return "user/findid";
	}

	
	// 입력받은 이메일에 ID 전송
	@ResponseBody
	@PostMapping(value = "/user/sendid")
	public boolean SendID(UserRequestDTO params1, MailDTO params2) {
		
		
		String id = userService.findID(params1);
		mailService.sendID(params2, id);
		
		return true;
	}
	
	
	// 비밀번호 찾기 페이지 진입
	@GetMapping(value = "/user/findpw")
	public String FindPW() {

		return "user/findpw";
	}
	
	
	// 입력받은 이메일과 ID가 DB에 있는지 확인하고 
	// 있다면 true를 반환하고 비밀번호를 임시비밀번호로 변경하고
	// 입력받은 이메일에 임시 비밀번호 전송
	// 없다면 false 반환
	@ResponseBody
	@PostMapping(value = "/user/sendpw")
	public boolean SendPW(UserRequestDTO params1, MailDTO params2) {
		
		boolean result = userService.eiCompare(params1);
		
		if(result == true) {
			String pw = userService.changePW(params1);
			mailService.sendPW(params2, pw);
		}
		
		return result;
	}
	
	 
	// 로그인 페이지 진입하기
	@GetMapping(value = "/user/login")
	public String logIn(@ModelAttribute("params") final SearchDto params, Model model) {
		model.addAttribute("userUrl", "/user/login");
		return "user/login";
	}
	
	
	@ResponseBody
	@PostMapping(value = "/user/login-check")
	public boolean LoginCheck(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {
		
		HashMap<String, Object> result = userService.loginCompare(params);
		
		
		if(result == null) {
			return false;
		} else {
			session.setAttribute("id", result.get("id"));
			session.setAttribute("admin_yn", result.get("admin_yn"));
			session.setAttribute("pw", result.get("pw"));
			session.setAttribute("name", result.get("name"));
			session.setAttribute("gender", result.get("gender"));
			session.setAttribute("nickname", result.get("nickname"));
			session.setAttribute("email", result.get("email"));
			
			// 세션 유지시간 30분
			session.setMaxInactiveInterval(30 * 60);
			
			return true;
		}
	}
	
	
	// id 세션 값 리턴
	@ResponseBody
	@PostMapping(value = "/get/id")
	public String GetID(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {
			
		String result = (String) session.getAttribute("id");
		
		return result;
		
	}
	
	
	// name 세션 값 리턴
	@ResponseBody
	@PostMapping(value = "/get/name")
	public String GetName(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {
			
		String result = (String) session.getAttribute("name");
		
		return result;
		
	}
	
	
	// gender 세션 값 리턴
	@ResponseBody
	@PostMapping(value = "/get/gender")
	public String GetGender(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {
			
		String result = (String) session.getAttribute("gender");
		
		if(result.equals("M"))
			return "남자";
		else
			return "여자";
		
	}
	
	
	// gender 세션 값 리턴
	@ResponseBody
	@PostMapping(value = "/get/nickname")
	public String GetNickname(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {
			
		String result = (String) session.getAttribute("nickname");
		
		return result;
	}
	
	
	// pw 세션 값 리턴
	@ResponseBody
	@PostMapping(value = "/get/pw")
	public String GetPw(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {
			
		String result = (String) session.getAttribute("pw");
		
		return result;
	}
	
	
	// 닉네임 업데이트
	@ResponseBody
	@PostMapping(value = "/user/update-nickname")
	public boolean NicknameUpdate(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {

		String result = userService.updateNickname(params, (String) session.getAttribute("email"));
		
		// 세션 값 업데이트
		session.setAttribute("nickname", result);
		
		return true;
	}
	
	
	// 비밀번호 업데이트
	@ResponseBody
	@PostMapping(value = "/user/update-pw")
	public boolean PWUpdate(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, UserRequestDTO params) {

		String result = userService.updatePw(params, (String) session.getAttribute("email"));
		
		// 세션 값 업데이트
		session.setAttribute("pw", result);
		
		return true;
	}
	
	
	// 마이페이지 진입하기
	@GetMapping(value = "/user/mypage")
	public String MyPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		return "user/mypage";
	}
	
	
	// 회원탈퇴 진입하기
	@GetMapping(value = "/user/withdrawal")
	public String Withdrawal() {
		
		return "user/withdrawal";
	}
	
	
	// 로그인 체크
	@ResponseBody
	@PostMapping(value = "/login-check")
	public boolean LoginCheck(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		if((String)session.getAttribute("id") == null || (String)session.getAttribute("pw") == null) {
			return false;
		} else {
			return true;
		}
	}
	
}