package com.spring.board.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		
		String a = sqlSession.selectOne("board.boardList");
		
		return a;
	}
	/**
	 * 
	 * */
	@Override
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception {
		return sqlSession.selectList("board.boardList",pageVo);
	}

	@Override
	public int selectBoardCnt(HashMap<String,Object> map) throws Exception {
		return sqlSession.selectOne("board.boardTotalSelect",map);
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardView", boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		return sqlSession.insert("board.boardInsert", boardVo);
	}/*글작성*/
	
	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		return sqlSession.update("board.boardUpdate", boardVo);
	}/*수정*/
	
	@Override
	public int boardDelete(BoardVo boardVo) {
		return sqlSession.delete("board.boardDelete", boardVo);
	}/*삭제*/
	
	@Override
	public List<ComCodeVo> boardTypeList() {
		return sqlSession.selectList("board.boardTypeList");
	}//조회 목록
	
	@Override
	public int boardInsert(List<BoardVo> b) {
		return sqlSession.insert("board.boardInserts", b);
	}//



	

	
	
}
