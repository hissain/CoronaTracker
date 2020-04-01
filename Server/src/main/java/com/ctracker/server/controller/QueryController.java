package com.ctracker.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctracker.server.model.CTQuery;
import com.ctracker.server.model.CTUser;
import com.ctracker.server.service.QueryProcessor;

@RestController
public class QueryController {

	@Autowired
	private QueryProcessor processor;

	@PostMapping("/fetch")
	List<CTUser> addUser(@RequestBody CTQuery queryParam) {

		if (queryParam.getAccessToken().equals("1234")) {
			return processor.process(queryParam);
		}
		return null;
	}
}