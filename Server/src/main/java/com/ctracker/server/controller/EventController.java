package com.ctracker.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctracker.server.data.JdbcUserRepository;
import com.ctracker.server.model.CTEvent;

@RestController
public class EventController {

	@Autowired
	private JdbcUserRepository jdbcService;

	@PostMapping("/events")
	String addEvent(@RequestBody CTEvent event) {

		return "Event added";
	}
}
