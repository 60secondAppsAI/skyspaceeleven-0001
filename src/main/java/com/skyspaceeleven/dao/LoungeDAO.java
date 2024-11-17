package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Lounge;





public interface LoungeDAO extends GenericDAO<Lounge, Integer> {
  
	List<Lounge> findAll();
	






}


