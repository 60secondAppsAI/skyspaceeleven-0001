package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Service;





public interface ServiceDAO extends GenericDAO<Service, Integer> {
  
	List<Service> findAll();
	






}


