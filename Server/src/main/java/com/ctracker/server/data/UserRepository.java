package com.ctracker.server.data;

import java.util.List;

public interface UserRepository {
	
	Long addUser(CoronaUser user);
	
	List<CoronaUser> findAll();

}
