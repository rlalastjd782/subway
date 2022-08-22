package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.users.dto.UserRequestDTO;
import com.board.users.mapper.UserMapper;

@SpringBootTest
public class SignUpTests {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testOfInsert() {
		try {
			System.out.println("insert 테스트 실행");
			UserRequestDTO params = new UserRequestDTO();
			params.setId("tester2");
			params.setPw("1q2w3e4r!");
			params.setName("테스터2");
			params.setNickname("테스터2");
			params.setGender("M");
			params.setEmail("abcde2@gmail.com");
			System.out.println("매퍼 실행하기전 인풋 데이터 : "+params);
			System.out.println(userMapper);
			userMapper.insertUser(params);
			System.out.println("insert 끝");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}