package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Ticket;





public interface TicketDAO extends GenericDAO<Ticket, Integer> {
  
	List<Ticket> findAll();
	






}


