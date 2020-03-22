package com.ctracker.server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CoronaUser> findAll() {

		return jdbcTemplate.query( "select * from users", (rs, rowNum) ->
            new CoronaUser(
                    rs.getLong("user_id"),
                    rs.getString("user_nid"),
                    rs.getString("user_name"),
                    rs.getString("user_duid")
            )
        );
	}

	@Override
	public Long addUser(CoronaUser user) {
		
		try {
			final String INSERT_SQL = "INSERT INTO users (user_nid, user_name, user_duid) VALUES (?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(INSERT_SQL, new String[] {"user_id"});
			            ps.setString(1, user.getUserNid());
			            ps.setString(2, user.getUserName());
			            ps.setString(3, user.getUserDuid());
			            return ps;
			        }
			    },
			    keyHolder);
				System.out.println(keyHolder);
				return keyHolder.getKey().longValue();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
}
