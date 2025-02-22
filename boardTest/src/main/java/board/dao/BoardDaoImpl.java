package board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDto;

public class BoardDaoImpl implements BoardDao {
	private JdbcTemplate jdbcTemplate;
	
		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate=jdbcTemplate;
		}
		
		
	@Override
	public List list() {
		String sql="select * from tblspringboard order by seq desc";
		List result= new ArrayList();
		RowMapper mapper = new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
				BoardDto dto=new BoardDto();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setHitcount(rs.getInt("hitcount"));
				dto.setPassword(rs.getString("password"));
				return dto;
			}
		};
		
		result = jdbcTemplate.query(sql, mapper);
		return result;
	}

	@Override
	public BoardDto findBySeq(int seq) {
		String sql="select * from tblspringboard where seq=?";
		BoardDto dto=(BoardDto) jdbcTemplate.query(sql, new BoardPreparedStatementSetterForPrimaryKey(seq),
				new UserResultSetExtractor());
				
		return dto;
	}

	private class BoardPreparedStatementSetterForPrimaryKey implements PreparedStatementSetter {
		private Integer seq;
		public BoardPreparedStatementSetterForPrimaryKey(Integer seq) {
			super();
			this.seq=seq;
		}
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, seq);
			
		}		
	}
	
	private class  UserResultSetExtractor implements ResultSetExtractor{

		@Override
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				BoardDto dto=new BoardDto();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setHitcount(rs.getInt("hitcount"));
				dto.setPassword(rs.getString("password"));
				return dto;
			}
			throw new DataRetrievalFailureException("해당 객체를 찾을 수 없습니다.");
		}	
	}
	
	

	@Override
	public void insert(BoardDto dto) {
		String sql ="insert into tblspringboard (title, content, writer, regdate, hitcount, password) "
				+ "values (?,?,?, now(), 0, '1234')";
		jdbcTemplate.update(sql, new BoardPreparedStatementSetterForInsert(dto.getTitle(),
				dto.getContent(), dto.getWriter()));

	}
	
	private class BoardPreparedStatementSetterForInsert implements PreparedStatementSetter {
		private String title;
		private String content;
		private String writer;
		public BoardPreparedStatementSetterForInsert(String title, 
				String content, String writer) {
			super();
			this.title=title;
			this.content=content;
			this.writer=writer;

		}
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, writer);	
		}
		
		
	}
	

	@Override
	public void update(BoardDto dto) {
		String sql ="update tblspringboard set title=?, content=? where seq=?";
				
		Object[] val = new Object[] { dto.getTitle(), dto.getContent(), dto.getSeq() };
		jdbcTemplate.update(sql,val);
	
	}

	@Override
	public void delete(int seq) {
		String sql ="delete from tblspringboard where seq=?";
		
		Object[] val = new Object[] { seq };
		jdbcTemplate.update(sql,val);

	}

}
