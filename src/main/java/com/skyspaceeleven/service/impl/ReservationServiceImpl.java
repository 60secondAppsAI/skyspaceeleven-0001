package com.skyspaceeleven.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspaceeleven.dao.GenericDAO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.service.impl.GenericServiceImpl;
import com.skyspaceeleven.dao.ReservationDAO;
import com.skyspaceeleven.domain.Reservation;
import com.skyspaceeleven.dto.ReservationDTO;
import com.skyspaceeleven.dto.ReservationSearchDTO;
import com.skyspaceeleven.dto.ReservationPageDTO;
import com.skyspaceeleven.dto.ReservationConvertCriteriaDTO;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import com.skyspaceeleven.service.ReservationService;
import com.skyspaceeleven.util.ControllerUtils;





@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Integer> implements ReservationService {

    private final static Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	ReservationDAO reservationDao;

	


	@Override
	public GenericDAO<Reservation, Integer> getDAO() {
		return (GenericDAO<Reservation, Integer>) reservationDao;
	}
	
	public List<Reservation> findAll () {
		List<Reservation> reservations = reservationDao.findAll();
		
		return reservations;	
		
	}

	public ResultDTO addReservation(ReservationDTO reservationDTO, RequestDTO requestDTO) {

		Reservation reservation = new Reservation();

		reservation.setReservationId(reservationDTO.getReservationId());


		reservation.setTimestamp(reservationDTO.getTimestamp());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		reservation = reservationDao.save(reservation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Reservation> getAllReservations(Pageable pageable) {
		return reservationDao.findAll(pageable);
	}

	public Page<Reservation> getAllReservations(Specification<Reservation> spec, Pageable pageable) {
		return reservationDao.findAll(spec, pageable);
	}

	public ResponseEntity<ReservationPageDTO> getReservations(ReservationSearchDTO reservationSearchDTO) {
	
			Integer reservationId = reservationSearchDTO.getReservationId(); 
   			String sortBy = reservationSearchDTO.getSortBy();
			String sortOrder = reservationSearchDTO.getSortOrder();
			String searchQuery = reservationSearchDTO.getSearchQuery();
			Integer page = reservationSearchDTO.getPage();
			Integer size = reservationSearchDTO.getSize();

	        Specification<Reservation> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, reservationId, "reservationId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Reservation> reservations = this.getAllReservations(spec, pageable);
		
		//System.out.println(String.valueOf(reservations.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(reservations.getTotalPages()));
		
		List<Reservation> reservationsList = reservations.getContent();
		
		ReservationConvertCriteriaDTO convertCriteria = new ReservationConvertCriteriaDTO();
		List<ReservationDTO> reservationDTOs = this.convertReservationsToReservationDTOs(reservationsList,convertCriteria);
		
		ReservationPageDTO reservationPageDTO = new ReservationPageDTO();
		reservationPageDTO.setReservations(reservationDTOs);
		reservationPageDTO.setTotalElements(reservations.getTotalElements());
		return ResponseEntity.ok(reservationPageDTO);
	}

	public List<ReservationDTO> convertReservationsToReservationDTOs(List<Reservation> reservations, ReservationConvertCriteriaDTO convertCriteria) {
		
		List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();
		
		for (Reservation reservation : reservations) {
			reservationDTOs.add(convertReservationToReservationDTO(reservation,convertCriteria));
		}
		
		return reservationDTOs;

	}
	
	public ReservationDTO convertReservationToReservationDTO(Reservation reservation, ReservationConvertCriteriaDTO convertCriteria) {
		
		ReservationDTO reservationDTO = new ReservationDTO();
		
		reservationDTO.setReservationId(reservation.getReservationId());

	
		reservationDTO.setTimestamp(reservation.getTimestamp());

	

		
		return reservationDTO;
	}

	public ResultDTO updateReservation(ReservationDTO reservationDTO, RequestDTO requestDTO) {
		
		Reservation reservation = reservationDao.getById(reservationDTO.getReservationId());

		reservation.setReservationId(ControllerUtils.setValue(reservation.getReservationId(), reservationDTO.getReservationId()));

		reservation.setTimestamp(ControllerUtils.setValue(reservation.getTimestamp(), reservationDTO.getTimestamp()));



        reservation = reservationDao.save(reservation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ReservationDTO getReservationDTOById(Integer reservationId) {
	
		Reservation reservation = reservationDao.getById(reservationId);
			
		
		ReservationConvertCriteriaDTO convertCriteria = new ReservationConvertCriteriaDTO();
		return(this.convertReservationToReservationDTO(reservation,convertCriteria));
	}







}
