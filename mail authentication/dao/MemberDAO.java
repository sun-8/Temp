package com.ywhy.dao;


import com.ywhy.vo.MemberVO;

public interface MemberDAO {

	MemberVO idCheck(String id);
	
	void insertMember(MemberVO m);

	int GetKey(String mem_id, String key);//회원가입 한 사람 인증 키 메서드
	int alter_memKey(String mem_id,String key);//유저 인증 키 Y로 바꾸는 메서드
	
	MemberVO loginCheck(String login_id);



}
