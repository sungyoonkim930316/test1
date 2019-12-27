package com.springboard.service;

import java.util.List;

import com.springboard.vo.BoardVO;

public interface BoardService {
	
	int writeBoard(BoardVO board);
	
	List<BoardVO> findBoard();
	
}
