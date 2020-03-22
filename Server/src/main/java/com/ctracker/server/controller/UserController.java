package com.ctracker.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctracker.server.data.*;
import com.ctracker.server.model.CTUser;
import com.ctracker.server.service.UserAuthorizer;

@RestController
public class UserController {
	
	@Autowired
	private JdbcUserRepository jdbcService;
	
	@Autowired
	private UserAuthorizer authorizer;
	
	@PostMapping("/users")
	CTUser addUser(@RequestBody CTUser user) {
		
		if (authorizer.isValidNid(user.getUserNid())) {
			Long res = jdbcService.addUser(user);
			if (res > 0) {
				user.setUserId(res);
				return user;
			}
		}
		return null;
	}
	
	@GetMapping("/users")
	List<CTUser> findUsers() {
		return jdbcService.findAll();
	}
}
