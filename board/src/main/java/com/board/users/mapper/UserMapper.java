package com.board.users.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.board.users.dto.UserRequestDTO;

@Mapper
public interface UserMapper {
	
	public int insertUser(UserRequestDTO params);
	
	public int updateUser(UserRequestDTO params);
	
	// 이미 존재하는 아이디라면 아이디를 반환, 아니면 null을 반환
	public String idCompare(UserRequestDTO params);

	public String nicknameCompare(UserRequestDTO params);
	
	public String emailCompare(UserRequestDTO params);
	
	public String searchID(UserRequestDTO params);

	public String searchPW(UserRequestDTO params);
	
	public int changePW(UserRequestDTO params);

	public String eiCompare(UserRequestDTO params);

	public HashMap<String, Object> loginCompare(UserRequestDTO params);
	
	public int changeNickname(UserRequestDTO params);
	
	public int changePw(UserRequestDTO params);

	public int deleteAccount(UserRequestDTO params);
}
