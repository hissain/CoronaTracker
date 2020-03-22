package com.ctracker.server.data;

import java.util.List;

import com.ctracker.server.model.CTUser;

public interface UserRepository {
	
	Long addUser(CTUser user);
	
	List<CTUser> findAll();

}
