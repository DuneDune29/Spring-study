package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;



@Repository
public class BoardDAOSpring {
	private final String BOARD_INSERT="insert into board(title, writer, content) values (?,?,?)";
	private final String BOARD_UPDATE="update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE="delete from board where seq=?";
	private final String BOARD_GET="select * from board where seq=?";
	private final String BOARD_LIST="select * from board order by seq desc";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void insertBoard(BoardVO vo) {
		System.out.println("Spring jdbc로 insertBoard 기능함");
		jdbcTemplate.update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());
	}
	
	public void updateBoard(BoardVO vo) {
		System.out.println("Spring jdbc로 updateBoard 기능함");
		jdbcTemplate.update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
	}
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("Spring jdbc로 deleteBoard 기능함");
		jdbcTemplate.update(BOARD_DELETE,vo.getSeq());
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("Spring jdbc로 getBoard 기능함");
		Object[] args= {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET,args,new BoardRowMapper());
		 
	}
	
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("Spring jdbc로 getBoardList 기능함");
		return jdbcTemplate.query(BOARD_LIST,new BoardRowMapper());
		
	}
	
}



class BoardRowMapper implements RowMapper<BoardVO>{
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		return board;
		
		
	}
}
