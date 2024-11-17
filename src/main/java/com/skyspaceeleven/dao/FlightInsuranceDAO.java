package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.FlightInsurance;





public interface FlightInsuranceDAO extends GenericDAO<FlightInsurance, Integer> {
  
	List<FlightInsurance> findAll();
	






}


