package com.springboard.mapper;

import java.util.List;

import com.springboard.vo.BoardVO;


// 인터페이스의 패키지명과 이름은 board-mapper.xml의 namespace와 일치하도록 작성.  
public interface BoardMapper {

	// 또한 인터페이스의 메서드는 board-mapper.xml의 select, insert등의 요소 정의와 일치하도록 작성.
	
	List<BoardVO> selectBoard();
	
	void insertBoard(BoardVO board);
	
}


