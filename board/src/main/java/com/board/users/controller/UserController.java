package com.board.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	 
	//	// 비밀번호 찾기 페이지 진입
	@GetMapping(value = "/user/signin")
	public String SingIn() {

		return "user/signin";
	}
	
}