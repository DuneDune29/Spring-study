package kr.co.mycom.guest.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class GuestDAOImpl implements GuestDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertGuest(GuestDTO dto) {
		String sql = "insert into guest(name, pwd, email,"
				+ " home, content, regdate) "
				+ " values(?,?,?,?,?, now())";
		
		Object[] arr = { dto.getName(), dto.getPwd(), dto.getEmail(),
				dto.getHome(), dto.getContent() };
		jdbcTemplate.update(sql, arr);
	}

	@Override
	public List listGuest() {
		String sql = "select * from guest order by no desc";
		
		return jdbcTemplate.queryForList(sql);
	}
}