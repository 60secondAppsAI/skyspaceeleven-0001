package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.CustomerServiceResponse;





public interface CustomerServiceResponseDAO extends GenericDAO<CustomerServiceResponse, Integer> {
  
	List<CustomerServiceResponse> findAll();
	






}


