package com.springboard.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.springboard.vo.BoardVO;

public class RawJdbcBoardDao implements BoardDao {
	
	@Override
	public int insertBoard(BoardVO board) {
		// 저수준 jdbc코드
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			// 2. 연결 만들기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
											   "springboard", "9922");
			// 3. 명령 만들기
			String sql = 
					"INSERT INTO tbl_board (bno, title, writer, content) " +
					"VALUES (seq_board.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			// 4. 명령 실행
			pstmt.executeUpdate(); // insert, update, delete를 위한 구문
			
			// 5. 결과가 있다면 결과 처리(select인 경우)
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return 0;
	}

	@Override
	public List<BoardVO> selectBoard() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> boards = new ArrayList<>(); // 조회 결과 저장 변수
		
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			// 2. 연결 만들기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
											   "springboard", "9922");
			// 3. 명령 만들기
			String sql = 
					"SELECT bno, title, writer, regdate, updatedate, deleted, readcount " +
					"FROM tbl_board ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 결과 처리(select문인 경우) -> 데이터 이동
			while (rs.next()) {
				// 한 행의 데이터를 읽어서 객체에 저장
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setRegDate(rs.getDate(4));
				board.setUpdateDate(rs.getDate(5));
				board.setDeleted(rs.getBoolean(6));
				board.setReadCount(rs.getInt(7));
				
				boards.add(board); // 한 행의 데이터를 결과 목록에 추가
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
			try { rs.close(); } catch (Exception ex) {}
		}
		
		return boards;
	}

}
