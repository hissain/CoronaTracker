package com.ctracker.server.data;

import java.util.List;

public interface UserRepository {
	
	List<CoronaUser> findAll();

}
