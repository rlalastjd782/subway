package com.board.users.service;

import com.board.users.dto.MailDTO;

public interface MailService {
	
	boolean certifyCompare(MailDTO params);

	void sendCode(MailDTO params);

	void sendID(MailDTO params, String id);

	void sendPW(MailDTO params, String pw);

}