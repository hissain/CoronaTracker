package com.ctracker.server.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctracker.server.data.JdbcEventRepository;
import com.ctracker.server.data.JdbcUserRepository;
import com.ctracker.server.model.CTEvent;
import com.ctracker.server.model.CTQuery;
import com.ctracker.server.model.CTUser;
import com.ctracker.server.utils.CTLocation;
import com.ctracker.server.utils.MathUtils;

@Service
public class QueryProcessor {

	@Autowired
	private JdbcUserRepository jdbcUser;

	@Autowired
	private JdbcEventRepository jdbcEvent;

	public Set<CTUser> process(CTQuery param) {
		
		System.out.println("process() called with id: " + param.getUserId());
		System.out.println("process() called with distance: " + param.getMaxContactDistance());
		System.out.println("process() called with day: " + param.getMaxDateCount());

		List<CTUser> users = jdbcUser.findAll();
		List<CTEvent> events = jdbcEvent.findAll();

		Set<CTUser> candidates = new HashSet<CTUser>();
		
		HashMap<Long, CTUser> map = new HashMap<>();
		
		for (int i = 0; i < users.size(); i++) {
			map.put(users.get(i).getUserId(), users.get(i));
		}

		//TODO: Efficient Filter Algorithm
		
		Long currentTime = System.currentTimeMillis();
		Long maxTime = param.getMaxDateCount() * 24 * 60 * 60 * 1000;
		
		System.out.println("process() currentTime: " + currentTime);
		System.out.println("process() maxTime: " + maxTime);
		
		List<CTEvent> userEvents = events.stream().filter(
				p -> p.getUserId() == param.getUserId() && (currentTime - p.getDatetime()) <= maxTime )
				.collect(Collectors.toList());
		
		System.out.println("process() userEvents: " + userEvents);
		
		for (int u = 0; u < userEvents.size(); u++) {
		
			CTLocation loc1 = new CTLocation();
			loc1.latitude = userEvents.get(u).getLatitude();
			loc1.longitude = userEvents.get(u).getLongitude();
			loc1.altitude = userEvents.get(u).getAltitude();
			
			for (int i = 0; i < events.size(); i++) {
				
				if (events.get(i).getUserId() != param.getUserId()) {
					
					CTLocation loc2 = new CTLocation();
					loc2.latitude = events.get(i).getLatitude();
					loc2.longitude = events.get(i).getLongitude();
					loc2.altitude = events.get(i).getAltitude();
					
					System.out.println("loc1: " + loc1);
					System.out.println("loc2: " + loc2);
					
					double distance = MathUtils.distance(loc1, loc2);
					
					System.out.println("distance: " + distance);
					
					if (distance <= param.getMaxContactDistance()) {
						candidates.add(map.get(events.get(i).getUserId()));
					}
				}
			}
		}
		
		return candidates;
	}
}
