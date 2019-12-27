package com.springboard.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.springboard.vo.BoardVO;

import lombok.Setter;

public class JdbcTemplateBoardDao implements BoardDao {
	
	@Setter
	private JdbcTemplate jdbcTemplate;
	
	// 1. 자동 증가 처리 포함하지 않는 구현
//	@Override
//	public int insertBoard(BoardVO board) {
//		
//		String sql = 
//			"INSERT INTO tbl_board (bno, title, writer, content) " +
//			"VALUES (seq_board.nextval, ?, ?, ?)";
//		
//		jdbcTemplate.update(sql, board.getTitle(), board.getWriter(), board.getContent());
//		
//		return 0;
//	}

	// 2. 자동 증가 처리 포함하는 구현
	@Override
	public int insertBoard(BoardVO board) {
		
		String sql = 
			"INSERT INTO tbl_board (bno, title, writer, content) " +
			"VALUES (seq_board.nextval, ?, ?, ?)";
		
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		// 이 위치에서 holder에 데이터가 없습니다.
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = 
						// (sql, insert 후에 조회할 자동 증가 컬럼 목록
						con.prepareStatement(sql, new String[] { "bno" });
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getWriter());
				pstmt.setString(3, board.getContent());
				return pstmt;
			}
		}, holder);
		// 이 위치에서 holder에 데이터가 있습니다.
		return holder.getKey().intValue();
	}

	@Override
	public List<BoardVO> selectBoard() {
		
		
		String sql = 
				"SELECT bno, title, writer, regdate, updatedate, deleted, readcount " +
				"FROM tbl_board ";
		
		//BoardRowMapper mapper = new BoardRowMapper();
		RowMapper<BoardVO> mapper = 
				// RowMapper<BoardVO>를 구현하는 이름 없는 클래스 만들기 + 그 클래스의 인스턴스 만들기 (new 이름 없는 클래스())
				new RowMapper<BoardVO>() {
			// mapRow : "조회결과 (ResultSet) -> 객체" 변환 처리 메서드
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt(1)); //여깄는 객체(BoardVO)가 db테이블과 data구조가 불일치해서 이 짓을 하고있다. 이게 Jdbc의 문제점중 하나고. myBatis는 객체와 sql을 매핑시켜주는 도구라고 보면된다.
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setRegDate(rs.getDate(4));
				board.setUpdateDate(rs.getDate(5));
				board.setDeleted(rs.getBoolean(6));
				board.setReadCount(rs.getInt(7));
				return board;
			}
		}; // sql에 select가 있으면 RowMapper 인터페이스를 구현해줘야 한다.
		
		List<BoardVO> boards = jdbcTemplate.query(sql, mapper);
		
		
		return boards;
	}

}

class BoardRowMapper implements RowMapper<BoardVO> {

	// mapRow : "조회결과 (ResultSet) -> 객체" 변환 처리 메서드
	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
		board.setBno(rs.getInt(1));
		board.setTitle(rs.getString(2));
		board.setWriter(rs.getString(3));
		board.setRegDate(rs.getDate(4));
		board.setUpdateDate(rs.getDate(5));
		board.setDeleted(rs.getBoolean(6));
		board.setReadCount(rs.getInt(7));
		return board;
	}
	
}
