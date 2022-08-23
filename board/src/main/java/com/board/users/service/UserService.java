package com.board.users.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.board.users.dto.UserRequestDTO;

public interface UserService {
	
	// DB에 유저 정보 입력
	public boolean userInsert(UserRequestDTO params);
	
	// 아직 안쓰임
	public boolean userInfoUpdate(UserRequestDTO params);
	
	// 아이디가 DB에 있는지 없는지 확인
	public boolean idCompare(UserRequestDTO params);
	
	// 닉네임이 DB에 있는지 없는지 확인
	public boolean nicknameCompare(UserRequestDTO params);

	// 이메일이 DB에 있는지 없는지 확인
	public boolean emailCompare(UserRequestDTO params);
	
	// 입력받은 이메일에 해당하는 ID가 있는지 확인
	public String findID(UserRequestDTO params);

	public String changePW(UserRequestDTO params);

	public boolean eiCompare(UserRequestDTO params);

	public HashMap<String, Object> loginCompare(UserRequestDTO params);

	public String updateNickname(UserRequestDTO params, String email);

	public String updatePw(UserRequestDTO params, String email);

}