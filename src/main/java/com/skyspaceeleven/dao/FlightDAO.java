package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


