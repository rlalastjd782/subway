package com.board.users.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRequestDTO {

	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{6,14}", message = "ID 길이 제한은 6~14글자이고, 영문과 숫자만 입력 가능합니다.")
	private String id;
	
	private int admin_yn;
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호 길이 제한은 8~16글자이고, 영문과 숫자, 특수문자가 모두 입력되야합니다.")
	private String pw;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@NotBlank
	private String gender;
	
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{3,12}$", message = "닉네임 길이 제한은 3~12글자이고, 한글과 영문, 숫자만 입력 가능합니다.")
	private String nickname;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	private String email;
	
	
}