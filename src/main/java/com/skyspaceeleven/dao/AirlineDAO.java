package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Airline;





public interface AirlineDAO extends GenericDAO<Airline, Integer> {
  
	List<Airline> findAll();
	






}


