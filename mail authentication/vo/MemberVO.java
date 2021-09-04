package com.ywhy.vo;


import lombok.Data;

@Data
public class MemberVO {

	private String mem_id;
	private String mem_pwd;
	private String mem_name; //이름
	private String mem_nick; //닉네임
	private String mem_mail;
	private String mem_key; //메일 인증 키
	private String mem_date; //가입날짜
	private int mem_class;
	
	//관리자 회원목록 페이징 관련 변수
	private int pageStart;
	private int pageEnd;
	
	//관리자 회원목록 검색필드와 관련 변수
	private String find_name;//검색어
	private String find_field;//검색필드
}
