package com.ywhy.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ywhy.service.MemberService;
import com.ywhy.service.UserMailSendService;
import com.ywhy.vo.MemberVO;

import pwdconv.PwdChange;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserMailSendService userMailSendService;
	
	/*로그인 폼*/
	@GetMapping("/login")
	public String login() {
		return "signup/login";
	}

	/*회원가입 폼*/
	@GetMapping("/signup")
	public String signup() {
		return "signup/signup";
	}
	
	/*아이디 중복 체크*/
	@PostMapping("/mem_idcheck")
	public String mem_idcheck(String id,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		MemberVO db_id=this.memberService.idCheck(id); //db로부터 아이디 검색
		
		int re=-1;
		if(db_id != null) { //id로 검색된 값이 있다면 (이미 존재하는 아이디라면)
			re=1;
		}
		out.println(re);
		return null;
	}
	
	/*회원 저장*/
	@PostMapping("/signup_ok")
	public String sign_ok(MemberVO m,HttpServletRequest request) {
		//MemberVO 빈클래스의 변수명과 member_join.jsp의 네임 파라미터 이름이 동일하면 m에 입력한 회원정보가 저장된다.
		System.out.println("비밀번호 암호화 전: "+m.getMem_pwd());
		m.setMem_pwd(PwdChange.getPassWordToXEMD5String(m.getMem_pwd()));//비밀번호 암호화
		System.out.println("비밀번호 암호화 후: "+m.getMem_pwd());
		this.memberService.insertMember(m);//회원저장
		
		this.userMailSendService.mailSendWithUserKey(m.getMem_mail(),m.getMem_id(),request);
		System.out.println("암호화된 메일 키: "+m.getMem_key());//java.sql.SQLException: 부적합한 열 유형: 1111
		return "redirect:/";
	}
	
	/*이메일 인증*/
	@GetMapping("/key_alter")
	public String key_alter(@RequestParam("mem_id") String mem_id, @RequestParam("key") String key) {
		this.userMailSendService.key_alter_service(mem_id,key);
		return "userSuccess";
	}
	
	/*로그인 인증*/
	@RequestMapping("/login_ok")
	public String login_ok(String login_id,String login_pwd,HttpSession session,HttpServletResponse response) throws Exception {
		response.setContentType("test/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		
		MemberVO m=this.memberService.loginCheck(login_id);
		//가입회원 1인 경우, 9인 경우 로그인 인증 처리
		//1은 일반사용자 / 9는 최고관리자가 지정한 관리자
		
		if(m == null) {
			out.println("<script>");
			out.println("alert('가입 안된 회원입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(!m.getMem_pwd().equals("sun")) {
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
			}else {
				session.setAttribute("id", login_id);
				return "login_user";
			}
		}
		return null;
	}
	
}
