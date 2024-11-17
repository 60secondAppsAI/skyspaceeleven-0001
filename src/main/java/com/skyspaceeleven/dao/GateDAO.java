package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Gate;





public interface GateDAO extends GenericDAO<Gate, Integer> {
  
	List<Gate> findAll();
	






}


