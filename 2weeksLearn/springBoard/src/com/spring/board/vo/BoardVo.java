package com.spring.board.vo;

import java.util.List;

public class BoardVo {
	
	private String 	boardType;
	private int 	boardNum;
	private String 	boardTitle;
	private String 	boardComment;
	private String 	creator;
	private String	modifier;
	private int totalCnt;
	private String code_name;
	
	private List<BoardVo> boardVoList;
	
//	private String[] boardType_list;
//	private String[] boardTitle_list;
//	private String[] boardComment_list;
	


	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	
	public List<BoardVo> getBoardVoList() {
		return boardVoList;
	}
	public void setBoardVoList(List<BoardVo> boardVoList) {
		this.boardVoList = boardVoList;
	}
	
	
//	public String[] getBoardType_list() {
//		return boardType_list;
//	}
//	public void setBoardType_list(String[] boardType_list) {
//		this.boardType_list = boardType_list;
//	}
//	public String[] getBoardTitle_list() {
//		return boardTitle_list;
//	}
//	public void setBoardTitle_list(String[] boardTitle_list) {
//		this.boardTitle_list = boardTitle_list;
//	}
//	public String[] getBoardComment_list() {
//		return boardComment_list;
//	}
//	public void setBoardComment_list(String[] boardComment_list) {
//		this.boardComment_list = boardComment_list;
//	}
	

	
	
	

	
	
	
	
	
}
