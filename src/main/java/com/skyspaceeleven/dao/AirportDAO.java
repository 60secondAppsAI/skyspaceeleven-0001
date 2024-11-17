package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Airport;





public interface AirportDAO extends GenericDAO<Airport, Integer> {
  
	List<Airport> findAll();
	






}


