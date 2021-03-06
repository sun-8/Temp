package com.spring.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*게시글 목록*/
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo) throws Exception{
		
//		List<BoardVo> boardList = new ArrayList<BoardVo>();//BoardVo타입을 사용한 List(게시글 목록)
//		List<ComCodeVo> boardTypeList = new ArrayList<ComCodeVo>();//ComCodeVo타입을 사용한 List(카테고리)
//		
//		int page = 1;//쪽페이지
//		int totalCnt = 0;//총 게시물 수
//		
//		if(pageVo.getPageNo() == 0){//pageVo의 pageNo변수의 값이 0일 때 1로 변경
//			pageVo.setPageNo(page);
//		}
//		
//		boardList = boardService.SelectBoardList(pageVo);//게시글 목록
//		boardTypeList = boardService.boardTypeList();//카테고리
//		totalCnt = boardService.selectBoardCnt(pageVo.getType());//총 게시글 수
//		
//		//model로 view에 전달
//		model.addAttribute("boardList", boardList);
//		model.addAttribute("totalCnt", totalCnt);
//		model.addAttribute("pageNo", page);
//		model.addAttribute("boardTypeList",boardTypeList);
		
		return "board/boardList";
	}
	
	/*게시글 조회*/
	@RequestMapping(value="board/boardListSort.do", method=RequestMethod.POST)
	@ResponseBody
	public String boardListSort(Locale locale,Model model,PageVo pageVo,String boardType,int boardNum)throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();//BoardVo타입을 사용한 List(게시글 목록)
		List<ComCodeVo> boardTypeList = new ArrayList<ComCodeVo>();//ComCodeVo타입을 사용한 List(카테고리)
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		CommonUtil commonUtil = new CommonUtil();
		
		boardList = boardService.SelectBoardList(pageVo);//게시글 목록
		boardTypeList = boardService.boardTypeList();//카테고리
		
		
		result.put("success", boardList);
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	/*게시글 상세보기*/
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		//@PathVariable 어노테이션 - URL경로에 변수를 넣어주는 것, {템플릿 변수}와 동일한 이름을 갖는 파라미터
		//						null이나 공백 값이 들어가는 파라미터는 적용하면 x
		//						값을 넘겨받을 때 .이 포함되어있으면 .을 포함해 뒤가 잘려서 들어간다.
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);//게시글 선택
		
		if(boardVo == null) {//게시글 선택한 것이 없으면 게시글목록으로 이동(게시글 목록에서 삭제된 글을 상세보기 했을 때)
			return "redirect:/board/boardList.do";
		}
		
		//model로 view에 전달
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	/*게시글 쓰기*/
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model,PageVo pageVo) throws Exception{

		List<ComCodeVo> boardTypeList = new ArrayList<ComCodeVo>();
		
		int page = 1;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		boardTypeList = boardService.boardTypeList();

		//model로 view에 전달
		model.addAttribute("pageNo", page);//boardWrite에 있는 script의 location에 사용
		model.addAttribute("boardTypeList", boardTypeList);
		
		return "board/boardWrite";
	}
	
	/*게시글 쓰기 완료*/
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int[] insert = new int[boardVo.getBoardVoList().size()];
		int resultCnt = 0;
		
		for(int i=0; i<boardVo.getBoardVoList().size(); i++) { 
			
//			boardVo.setBoardType(boardVo.getBoardType_list()[i]);
//			boardVo.setBoardTitle(boardVo.getBoardTitle_list()[i]);
//			boardVo.setBoardComment(boardVo.getBoardComment_list()[i]);
			
			System.out.println(i);
			System.out.println(boardVo.getBoardVoList().get(i).getBoardType());
			System.out.println(boardVo.getBoardVoList().get(i).getBoardTitle());
			System.out.println(boardVo.getBoardVoList().get(i).getBoardComment());
			
//			try {
//				insert[i] = boardService.boardInsert(boardVo.getBoardVoList().get(i));
//				resultCnt += insert[i];
//			}catch(Exception e) {
//				continue;
//			}
			
			if(boardVo.getBoardVoList().get(i).getBoardType() == null) {
				continue;
			}
			/*반대로 말하면 null이 아닐 때도 if문으로 쓸 수 있다*/
			
			insert[i] = boardService.boardInsert(boardVo.getBoardVoList().get(i));
			resultCnt += insert[i];
		}
		/*
		 * BoardVo에 배열 변수를 새로 만들어 boardWrite에 적용
		 * boardWrite에서 여러 게시글을 적을 수록 배열 변수에 저장된다
		 * controller에서 저장된 배열의 크기만큼 for반복문을 사용하여 각 인덱스값들을 원래 저장하려고 했던 변수에 저장
		 * insert도 반복문 안에 넣어서 작성한 게시글 만큼 반복되도록 구현
		 */

		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);//?
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	/*게시글 수정*/
	@RequestMapping(value="board/boardModify.do", method=RequestMethod.GET)
	public String boardModify(Locale locale,Model model,String boardType,int boardNum,PageVo pageVo) throws Exception{
		
		//List<ComCodeVo> boardTypeList = new ArrayList<ComCodeVo>();//ComCodeVo타입을 사용한 List(카테고리)
		
		int page = 1;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		BoardVo boardVo = boardService.selectBoard(boardType, boardNum);//게시글 선택
		//boardTypeList = boardService.boardTypeList();//카테고리
		
		//model로 view에 전달
		model.addAttribute("boardType", boardType);//boardModify에 있는 script의 location에 사용
		model.addAttribute("boardNum", boardNum);//boardModify에 있는 script의 location에 사용
		model.addAttribute("board", boardVo);
		model.addAttribute("pageNo", page);//boardModify에 있는 script의 location에 사용
		//model.addAttribute("boardTypeList", boardTypeList);
		
		return "board/boardModify";
	}
	
	/*게시글 수정 완료*/
	@RequestMapping(value = "/board/boardModifyAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardModifyAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	/*게시글 삭제*/
	@RequestMapping(value="board/boardDelAction.do", method=RequestMethod.POST)
	@ResponseBody
	public String boardDel(Locale locale,Model model,String boardType,int boardNum)throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardDelete(boardType,boardNum);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
}
