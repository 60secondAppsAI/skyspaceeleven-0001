package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Crew;





public interface CrewDAO extends GenericDAO<Crew, Integer> {
  
	List<Crew> findAll();
	






}


