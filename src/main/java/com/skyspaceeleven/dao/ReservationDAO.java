package com.skyspaceeleven.dao;

import java.util.List;

import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.domain.Reservation;





public interface ReservationDAO extends GenericDAO<Reservation, Integer> {
  
	List<Reservation> findAll();
	






}


