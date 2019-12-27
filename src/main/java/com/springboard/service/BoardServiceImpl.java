package com.springboard.service;

import java.util.List;

import com.springboard.mapper.BoardMapper;
import com.springboard.repository.BoardDao;
import com.springboard.repository.RawJdbcBoardDao;
import com.springboard.vo.BoardVO;

import lombok.Setter;

public class BoardServiceImpl implements BoardService {

	@Setter // setBoardDao를 자동으로 포함하는 annotation (롬복한테 요청)
	private BoardDao boardDao;
	
	@Setter
	private BoardMapper boardMapper;
	
	@Override
	public int writeBoard(BoardVO board) {
		
		//BoardDao boardDao = new RawJdbcBoardDao();
		//int newBno = boardDao.insertBoard(board);
		
		boardMapper.insertBoard(board);
		return board.getBno();

	}

	@Override
	public List<BoardVO> findBoard() {
		
		//return boardDao.selectBoard();
		return boardMapper.selectBoard();
		
	}

}
