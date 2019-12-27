package com.springboard.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.springboard.mapper.BoardMapper;
import com.springboard.vo.BoardVO;

import lombok.Setter;

public class MyBatisMapperBoardDao implements BoardDao {
	
	@Setter
	private BoardMapper boardMapper;
	
	@Override
	public int insertBoard(BoardVO board) {
		
		//board.getBno() -> 0이 나온다. (비어 있는 데이터)
		boardMapper.insertBoard(board); // 문자열로 처리하던걸 코드로 처리하게끔 수정했다. MyBatisBoardDao랑 root-context.xml참조
		
		return board.getBno(); // -> 새로 등록된 글 번호 반환
	}

	@Override
	public List<BoardVO> selectBoard() {
		
		List<BoardVO> boards = boardMapper.selectBoard();
		
		return boards;
	}
	// 이러고 보니까 급기야 Dao에서 하는일이라곤 그저 Mapper를 호출하고 반환하는 짓밖에 없는데 이럴꺼면 왜 만드나 이런 생각까지 들기 시작했다.
	// BoardServiceImpl 참조. 바로 서비스에 의존성 주입 시켜서 사용하는 것이다.
}