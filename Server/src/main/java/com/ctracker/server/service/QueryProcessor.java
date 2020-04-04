package com.ctracker.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

	public List<CTUser> process(CTQuery param) {
		
		System.out.println("process() called with id: " + param.getUserId());
		System.out.println("process() called with distance: " + param.getMaxContactDistance());
		System.out.println("process() called with day: " + param.getMaxDateCount());

		List<CTUser> users = jdbcUser.findAll();
		List<CTEvent> events = jdbcEvent.findAll();

		List<CTUser> candidates = new ArrayList<CTUser>();
		
		HashMap<Long, CTUser> map = new HashMap<>();
		
		for (int i = 0; i < users.size(); i++) {
			map.put(users.get(i).getUserId(), users.get(i));
		}

		//TODO: Efficient Filter Algorithm
		//1. Process users and events
		//2. Filter candidates from users which satisfy conditions sent by param
		//3. Below is an example, lets say we found all candidate for the param userId, thus
		//candidates.addAll(users);
		//3. Return filtered candidates
		
		//Comparator<CTUser> compareByDate = (CTUser user1, CTUser user2) ->  user1.ge.compareTo( o2.getId() );
		
		Long currentTime = System.currentTimeMillis();
		Long maxTime = param.getMaxDateCount() * 24 * 60 * 60 * 1000;
		
		System.out.println("process() currentTime: " + currentTime);
		System.out.println("process() maxTime: " + maxTime);
		
		List<CTEvent> userEvents = events.stream().filter(
				p -> p.getUserId() == param.getUserId() && (currentTime - p.getDatetime()) <= maxTime )
				.collect(Collectors.toList());
		
		System.out.println("process() userEvents: " + userEvents);
		
		for (int u = 0; u < userEvents.size(); u++) {
			
			if (events.get(u).getUserId() != param.getUserId()) {
			
				CTLocation loc1 = new CTLocation(); 
				loc1.latitude = events.get(u).getLatitude();
				loc1.longitude = events.get(u).getLongitude();
				loc1.altitude = events.get(u).getAltitude();
				
				for (int i = 0; i < events.size(); i++) {
					
					CTLocation loc2 = new CTLocation(); 
					loc2.latitude = events.get(i).getLatitude();
					loc2.longitude = events.get(i).getLongitude();
					loc2.altitude = events.get(i).getAltitude();
					
					System.out.println("process() loc1: " + loc1);
					System.out.println("process() loc2: " + loc2);
					
					if (MathUtils.distance(loc1, loc2) <= param.getMaxContactDistance()) {
						candidates.add(map.get(events.get(i).getUserId()));
					}
				}
			}
		}
		
		return candidates;
	}
}
