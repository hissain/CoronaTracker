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

import com.ctracker.server.model.CTEvent;

@Repository
public class JdbcEventRepository implements EventRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<CTEvent> findAll() {

		return jdbcTemplate.query( "select * from events", (rs, rowNum) ->
            new CTEvent(
                    rs.getLong("event_id"),
                    rs.getLong("user_id"),
                    rs.getFloat("event_lat"),
                    rs.getFloat("event_lon"),
                    rs.getFloat("event_alt"),
                    rs.getTimestamp("event_time").getTime()
            )
        );
	}

	@Override
	public Long addEvent(CTEvent event) {

		try {
			final String INSERT_SQL = "INSERT INTO events (user_id, event_lat, event_lon, event_alt) VALUES (?, ?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(INSERT_SQL, new String[] {"event_id"});
			            ps.setLong(1, event.getUserId());
			            ps.setFloat(2, event.getLatitude());
			            ps.setFloat(3, event.getLongitude());
			            ps.setFloat(4, event.getAltitude());
			            return ps;
			        }
			    },
			    keyHolder);
				return keyHolder.getKey().longValue();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
}
