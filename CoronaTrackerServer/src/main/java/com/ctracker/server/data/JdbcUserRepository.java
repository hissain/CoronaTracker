package com.ctracker.server.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
}
