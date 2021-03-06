package com.spring.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception {
		return boardDao.selectBoardList(pageVo);
	}
	
	@Override
	public int selectBoardCnt(String[] type) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("type",type);
		return boardDao.selectBoardCnt(map);
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		return boardDao.boardInsert(boardVo);
	}/*글쓰기*/

	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		return boardDao.boardUpdate(boardVo);
	}/*수정*/

	@Override
	public int boardDelete(String boardType, int boardNum) {
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.boardDelete(boardVo);
	}/*삭제*/

	@Override
	public List<ComCodeVo> boardTypeList() {
		return boardDao.boardTypeList();
	}//조회 목록

	@Override
	public int boardInsert(List<BoardVo> b) {
		return boardDao.boardInsert(b);
	}//




	
}
