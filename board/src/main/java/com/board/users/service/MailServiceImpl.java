package com.board.users.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.board.users.dto.MailDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

	private JavaMailSender emailSender;

	@Override
	public void sendCode(MailDTO params) {
		// TODO Auto-generated method stub

		String code = params.getCode();
		String content = "인증번호는 " + code + " 입니다.";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("metromailsender@google.com");
		message.setTo(params.getEmail());
		message.setSubject("인증번호 발신 메일입니다.");
		message.setText(content);

		emailSender.send(message);
	}

	@Override
	public boolean certifyCompare(MailDTO params) {
		// TODO Auto-generated method stub

		String code = params.getCode();
		
		if(params.getCertify().equals(code)) { 
			return true; 
		} else { 
			return false;
		}
	}

	@Override
	public void sendID(MailDTO params, String id) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("metromailsender@google.com");
		message.setTo(params.getEmail());
		message.setSubject("아이디 찾기 메일입니다.");
		message.setText("아이디는" + id + " 입니다.");

		emailSender.send(message);
	}

	
	@Override
	public void sendPW(MailDTO params, String pw) {
		// TODO Auto-generated method stub
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("metromailsender@google.com");
		message.setTo(params.getEmail());
		message.setSubject("임시 비밀번호 전송 메일입니다.");
		message.setText("임시 비밀번호는 " + pw + " 입니다.");

		emailSender.send(message);
		
	}

}