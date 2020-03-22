package com.ctracker.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctracker.server.data.JdbcEventRepository;
import com.ctracker.server.model.CTEvent;

@RestController
public class EventController {

	@Autowired
	private JdbcEventRepository jdbcService;

	@PostMapping("/events")
	CTEvent addEvent(@RequestBody CTEvent event) {

		Long res = jdbcService.addEvent(event);
		if (res > 0) {
			event.setEventId(res);
			return event;
		}
		return null;
	}

	@GetMapping("/events")
	List<CTEvent> findEvents() {
		return jdbcService.findAll();
	}
}
