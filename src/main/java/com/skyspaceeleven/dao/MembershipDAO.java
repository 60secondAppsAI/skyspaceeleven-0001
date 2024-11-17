package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Membership;





public interface MembershipDAO extends GenericDAO<Membership, Integer> {
  
	List<Membership> findAll();
	






}


