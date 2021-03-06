package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;

public interface boardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception;//전체목록

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;

	public int selectBoardCnt(String[] type) throws Exception;

	public int boardInsert(BoardVo boardVo)throws Exception;

	public int boardUpdate(BoardVo boardVo) throws Exception;//수정

	public int boardDelete(String boardType, int boardNum);//삭제

	public List<ComCodeVo> boardTypeList();//조회 목록

	public int boardInsert(List<BoardVo> b);////







}
