package com.ctracker.server.data;

import java.util.List;

import com.ctracker.server.model.CTEvent;

public interface EventRepository {

	Long addEvent(CTEvent event);

	List<CTEvent> findAll();
}
