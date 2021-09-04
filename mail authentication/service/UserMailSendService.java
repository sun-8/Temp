package com.ywhy.service;

import javax.servlet.http.HttpServletRequest;

public interface UserMailSendService {

	void mailSendWithUserKey(String mem_mail, String mem_id, HttpServletRequest request);

	int key_alter_service(String mem_id, String key);
	//반환타입을 void에서 int로 바꿨다.

}
