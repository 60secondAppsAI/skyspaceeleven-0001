package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Insurance;





public interface InsuranceDAO extends GenericDAO<Insurance, Integer> {
  
	List<Insurance> findAll();
	






}


