package com.board.users.service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.users.dto.UserRequestDTO;
import com.board.users.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean userInfoUpdate(UserRequestDTO params){
		
		int queryResult = 0;
		
		// 컨트롤러에서 넘어온 id에 대한 if문
		if(params.getId() == null) {
			// id가 없을 경우 신규 가입
			queryResult = userMapper.insertUser(params);
		} else {
			// id가 있을 경우 마이페이지에서 회원정보 수정
			queryResult = userMapper.updateUser(params);
		}
		
		return (queryResult == 1)? true : false;
		
	}

	
	// 입력받은 id가 DB에 있는지 없는지 검사
	@Override
	public boolean idCompare(UserRequestDTO params) {
		// TODO Auto-generated method stub
		
		String result = userMapper.idCompare(params);
		
		if(result == null) {
			// 폼에 입력받은 아이디가 없다면 true
			return true; 
		} else {
			//있으면 false
			return false;
		}
		
	}
	
	
	// 입력받은 닉네임이 DB에 있는지 없는지 검사
	@Override
	public boolean nicknameCompare(UserRequestDTO params) {
		// TODO Auto-generated method stub
		
		String result = userMapper.nicknameCompare(params);
		
		if(result == null) {
			// 폼에 입력받은 아이디가 없다면 true
			return true; 
		} else {
			//있으면 false
			return false;
		}
		
	}
	
	
	// 입력받은 이메일이 DB에 있는지 없는지 검사
	@Override
	public boolean emailCompare(UserRequestDTO params) {
		// TODO Auto-generated method stub
		
		String result = userMapper.emailCompare(params);
		
		if(result == null) {
			// 폼에 입력받은 아이디가 없다면 true
			return true; 
		} else {
			//있으면 false
			return false;
		}
		
	}


	// DB에 회원가입 정보 넣기
	@Override
	public boolean userInsert(UserRequestDTO params) {
		
		// DB 넣기 전 최종 중복 확인
		String result1 = userMapper.emailCompare(params);
		String result2 = userMapper.nicknameCompare(params);
		String result3 = userMapper.emailCompare(params);
		
		if(result1 == null && result2 == null && result3 == null) {
			
			// 폼에 입력받은 값을 DB에 전달
			userMapper.insertUser(params);
			return true; 
		} else {
		
			return false;
		}
	}


	// ID 찾기
	@Override
	public String findID(UserRequestDTO params) {
		// TODO Auto-generated method stub
		
		// 입력받은 이메일에 해당되는 id 찾기
		String result = userMapper.searchID(params);
		
		return result;
	}


	// 비밀번호를 임시비밀번호로 변경 및 이메일에 전송
	@Override
	public String changePW(UserRequestDTO params) {
		// TODO Auto-generated method stub
		
		
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<10; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }
        
        // 임시 비밀번호 : sb.toString();


		params.setPw(sb.toString());
		
		userMapper.changePW(params);
		
		String result = userMapper.searchPW(params);
		
		return result;
	}


	@Override
	public boolean eiCompare(UserRequestDTO params) {
		// TODO Auto-generated method stub
		
		String result = userMapper.eiCompare(params);
		
		if(result == null) {
			// DB에 이메일과 ID가 없으면 false
			return false; 
		} else {
			//있으면 true
			return true;
		}
	}


	@Override
	public HashMap<String, Object> loginCompare(UserRequestDTO params) {
		// TODO Auto-generated method stub

		HashMap<String, Object> result = userMapper.loginCompare(params);

		if(result == null)
			return null;
		else
			return result;
	}

	
}
