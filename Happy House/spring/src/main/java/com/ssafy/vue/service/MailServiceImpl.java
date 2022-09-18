package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean sendMail(String email, String regKey) {
		SimpleMailMessage sm = new SimpleMailMessage();

		sm.setTo(email);
		sm.setSubject("HappyHouse 인증 코드입니다.");
		sm.setText("당신의 인증 코드는 " + regKey + " 입니다.");

		try {
			javaMailSender.send(sm);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
