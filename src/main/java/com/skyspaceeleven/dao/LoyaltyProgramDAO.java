package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.LoyaltyProgram;





public interface LoyaltyProgramDAO extends GenericDAO<LoyaltyProgram, Integer> {
  
	List<LoyaltyProgram> findAll();
	






}


