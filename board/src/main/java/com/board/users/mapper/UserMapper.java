package com.board.users.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.board.users.dto.UserRequestDTO;

@Mapper
public interface UserMapper {
	
	// DB에 입력받은 값 넣기
	public int insertUser(UserRequestDTO params);
	
	// 비밀번호와 닉네임 변경
	public int updateUser(UserRequestDTO params);
	
	// 이미 존재하는 아이디라면 아이디를 반환, 아니면 null을 반환
	public String idCompare(UserRequestDTO params);

	public String nicknameCompare(UserRequestDTO params);
	
	public String emailCompare(UserRequestDTO params);
	
	// DB에 입력받은 아이디가 있는지 확인
	public String searchID(UserRequestDTO params);

	public String searchPW(UserRequestDTO params);
	
	// 임시 비밀번호로 변경
	public int changePW(UserRequestDTO params);

	public String eiCompare(UserRequestDTO params);

	// 로그인 정보 확인
	public HashMap<String, Object> loginCompare(UserRequestDTO params);
	
	// 닉네임 변경
	public int changeNickname(UserRequestDTO params);
	
	// 비밀번호 변경
	public int changePw(UserRequestDTO params);

	// 회원 탈퇴
	public int deleteAccount(UserRequestDTO params);
}
