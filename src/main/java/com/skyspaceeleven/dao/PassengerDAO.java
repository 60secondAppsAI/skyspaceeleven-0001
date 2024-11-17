package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Passenger;





public interface PassengerDAO extends GenericDAO<Passenger, Integer> {
  
	List<Passenger> findAll();
	






}


