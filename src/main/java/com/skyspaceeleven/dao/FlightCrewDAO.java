package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.FlightCrew;





public interface FlightCrewDAO extends GenericDAO<FlightCrew, Integer> {
  
	List<FlightCrew> findAll();
	






}


