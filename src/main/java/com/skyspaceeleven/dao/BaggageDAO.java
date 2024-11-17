package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Baggage;





public interface BaggageDAO extends GenericDAO<Baggage, Integer> {
  
	List<Baggage> findAll();
	






}


