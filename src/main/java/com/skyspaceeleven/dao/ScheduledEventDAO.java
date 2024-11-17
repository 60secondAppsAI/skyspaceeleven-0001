package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.ScheduledEvent;





public interface ScheduledEventDAO extends GenericDAO<ScheduledEvent, Integer> {
  
	List<ScheduledEvent> findAll();
	






}


