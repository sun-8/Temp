package com.spring.board.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception;//전체목록

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt(HashMap<String,Object> map) throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;

	public int boardUpdate(BoardVo boardVo) throws Exception;//수정

	public int boardDelete(BoardVo boardVo);//삭제

	public List<ComCodeVo> boardTypeList();//조회 목록

	public int boardInsert(List<BoardVo> b);//






}
