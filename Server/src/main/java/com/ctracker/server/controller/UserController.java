package com.ctracker.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctracker.server.data.*;

@RestController
public class UserController {
	
	@Autowired
	JdbcUserRepository jdbcService;
	
	@PostMapping("/users")
	String addUser() {
		
		return "User added";
	}
	
	@GetMapping("/users")
	List<CoronaUser> findUsers() {
		return jdbcService.findAll();
	}
	
}
