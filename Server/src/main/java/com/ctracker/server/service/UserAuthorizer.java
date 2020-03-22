package com.ctracker.server.service;

import org.springframework.stereotype.Service;

@Service
public class UserAuthorizer {

	public boolean isValidNid(String nid) {
		
		// check with API for services.nidw.gov.bd
		return true;
	}
}
