package com.ywhy.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ywhy.dao.MemberDAO;

@Service
public class UserMailSendServiceImpl implements UserMailSendService {

	//@Autowired//를 하면 에러를 발생시키고 지우면 에러가 안난다..? 
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private MemberDAO memberDao;
	
	//이메일 난수 만드는 메서드
	private String init() {
		Random r=new Random();
		StringBuffer sb=new StringBuffer();
		int num=0;
		
		do {
			num = r.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}
	
	//난수만드는 메서드를 이용해서 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	@Override
	public void mailSendWithUserKey(String mem_mail, String mem_id, HttpServletRequest request) {
		String key = getKey(false, 20);
		System.out.println(key);
		memberDao = sqlSessionTemplate.getMapper(MemberDAO.class);
		memberDao.GetKey(mem_id, key); 
		MimeMessage mail = javaMailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 MS :p 관리자 입니다.</h2><br><br>" 
				+ "<h3>" + mem_id + "님</h3>" + "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://localhost:8088" + request.getContextPath() + "/login_key_alter?mem_id="+ mem_id +"&mem_key="+key+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		//get방식으로 보낼 url을 작성하는데 param 값으로 user_id와 key값을 보낸다.
		//만약 mem_id와 key값이 동일하다면 key값을 특정 값으로 바꿔 이메일 인증상태로 바꿔준다.
		try {
			mail.setSubject("[본인인증] MS :p 민수르님의 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(mem_mail));
			javaMailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int key_alter_service(String mem_id, String key) {
		int resultCont=0;
		this.memberDao=sqlSessionTemplate.getMapper(MemberDAO.class);
		resultCont=this.memberDao.alter_memKey(mem_id, key);
		
		return resultCont;
	}
}
