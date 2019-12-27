package com.springboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.service.BoardService;
import com.springboard.service.BoardServiceImpl;
import com.springboard.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(path = { "/board" })
@Log4j // 좆복이 로그변수를 자동으로 생성
public class BoardController {
	
	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	@GetMapping(path = { "/list.action" })
	public String list(Model model) { // 목록보기
		
		// 데이터 조회 (서비스에 요청)
		List<BoardVO> boards = boardService.findBoard();
		
		// Model 타입 전달인자에 데이터 저장 -> View로 전달
		// (실제로는 Request 객체에 데이터 저장)
		model.addAttribute("boards", boards);
		
		return "board/list"; // /WEB-INF/views + board/list + .jsp
	}
	
	@GetMapping(path = { "/write.action" })
	public String showWriteForm() { // 글쓰기 화면 보기
		
		return "board/write";
	}
	
	@PostMapping(path = { "/write.action" })
	public String write(BoardVO board, RedirectAttributes attr) { // 글쓰기 처리
		
		//BoardService boardService = new BoardServiceImpl();
		int newBoardNo = boardService.writeBoard(board);
		log.warn("NEW BOARD NO : " + newBoardNo);
		
		//1. GET 방식으로 데이터 전달
		//return "redirect:list.action?newBno=" + newBoardNo;
		//attr.addAttribute("newBno", newBoardNo);
		
		//2. 스프링의 기능을 사용해서 데이터 전달
		attr.addFlashAttribute("newBno", newBoardNo); //addFlashAttribute : session에 데이터 저장
		return "redirect:list.action";
	}

}
