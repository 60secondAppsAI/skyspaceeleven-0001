package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Aircraft;





public interface AircraftDAO extends GenericDAO<Aircraft, Integer> {
  
	List<Aircraft> findAll();
	






}


