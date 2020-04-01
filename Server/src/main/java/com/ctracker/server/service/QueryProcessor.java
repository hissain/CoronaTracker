package com.ctracker.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctracker.server.data.JdbcEventRepository;
import com.ctracker.server.data.JdbcUserRepository;
import com.ctracker.server.model.CTEvent;
import com.ctracker.server.model.CTQuery;
import com.ctracker.server.model.CTUser;

@Service
public class QueryProcessor {

	@Autowired
	private JdbcUserRepository jdbcUser;

	@Autowired
	private JdbcEventRepository jdbcEvent;

	public List<CTUser> process(CTQuery param) {

		List<CTUser> users = jdbcUser.findAll();
		List<CTEvent> events = jdbcEvent.findAll();

		List<CTUser> candidates = new ArrayList<CTUser>();

		//TODO: Efficient Filter Algorithm
		//1. Process users and events
		//2. Filter candidates from users which satisfy conditions sent by param
		//3. Below is an example, lets say we found all candidate for the param userId, thus
		candidates.addAll(users);
		//3. Return filtered candidates

		return candidates;
	}
}
