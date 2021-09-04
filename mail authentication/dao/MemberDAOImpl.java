package com.ywhy.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ywhy.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO idCheck(String id) {
		return this.sqlSession.selectOne("id_check",id);
	}//아이디 중복확인
	
	@Override
	public void insertMember(MemberVO m) {
		this.sqlSession.insert("mem_insert", m);
	}//회원가입
	
	@Override
	public int GetKey(String mem_id, String key) {
		return this.sqlSession.update(mem_id,key);
	}//메일 인증 키 받아오기

	@Override
	public int alter_memKey(String mem_id, String key) {
		return this.sqlSession.update(mem_id,key);
	}//메일 인증 확인
	
	@Override
	public MemberVO loginCheck(String login_id) {
		return this.sqlSession.selectOne("login_check", login_id);
	}



}
