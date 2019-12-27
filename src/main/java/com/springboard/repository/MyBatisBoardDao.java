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

import com.springboard.vo.BoardVO;

import lombok.Setter;

public class MyBatisBoardDao implements BoardDao {
	
	private final String MAPPER = "com.springboard.mapper.BoardMapper."; // 이런식으로 반복적인 코드를 추가로 또 줄일 수 있다.
	
	@Setter
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public int insertBoard(BoardVO board) {
		
		//board.getBno() -> 0이 나온다. (비어 있는 데이터)
		sessionTemplate.insert(
				//"com.springboard.mapper.BoardMapper.insertBoard", board);
				MAPPER + "insertBoard", board); // 여기가 문자열이면 실행할때 외에는 오류메세지를 못봐서 코드로 처리하고 싶다.
		
		return board.getBno(); // -> 새로 등록된 글 번호 반환
	}

	@Override
	public List<BoardVO> selectBoard() {
		
		List<BoardVO> boards = sessionTemplate.selectList(
				//"com.springboard.mapper.BoardMapper.selectBoard");
				MAPPER + "selectBoard");
		
		return boards;
	}

}