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
import com.skyspaceeleven.dao.FlightInsuranceDAO;
import com.skyspaceeleven.domain.FlightInsurance;
import com.skyspaceeleven.dto.FlightInsuranceDTO;
import com.skyspaceeleven.dto.FlightInsuranceSearchDTO;
import com.skyspaceeleven.dto.FlightInsurancePageDTO;
import com.skyspaceeleven.dto.FlightInsuranceConvertCriteriaDTO;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import com.skyspaceeleven.service.FlightInsuranceService;
import com.skyspaceeleven.util.ControllerUtils;





@Service
public class FlightInsuranceServiceImpl extends GenericServiceImpl<FlightInsurance, Integer> implements FlightInsuranceService {

    private final static Logger logger = LoggerFactory.getLogger(FlightInsuranceServiceImpl.class);

	@Autowired
	FlightInsuranceDAO flightInsuranceDao;

	


	@Override
	public GenericDAO<FlightInsurance, Integer> getDAO() {
		return (GenericDAO<FlightInsurance, Integer>) flightInsuranceDao;
	}
	
	public List<FlightInsurance> findAll () {
		List<FlightInsurance> flightInsurances = flightInsuranceDao.findAll();
		
		return flightInsurances;	
		
	}

	public ResultDTO addFlightInsurance(FlightInsuranceDTO flightInsuranceDTO, RequestDTO requestDTO) {

		FlightInsurance flightInsurance = new FlightInsurance();

		flightInsurance.setFlightInsuranceId(flightInsuranceDTO.getFlightInsuranceId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		flightInsurance = flightInsuranceDao.save(flightInsurance);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<FlightInsurance> getAllFlightInsurances(Pageable pageable) {
		return flightInsuranceDao.findAll(pageable);
	}

	public Page<FlightInsurance> getAllFlightInsurances(Specification<FlightInsurance> spec, Pageable pageable) {
		return flightInsuranceDao.findAll(spec, pageable);
	}

	public ResponseEntity<FlightInsurancePageDTO> getFlightInsurances(FlightInsuranceSearchDTO flightInsuranceSearchDTO) {
	
			Integer flightInsuranceId = flightInsuranceSearchDTO.getFlightInsuranceId(); 
 			String sortBy = flightInsuranceSearchDTO.getSortBy();
			String sortOrder = flightInsuranceSearchDTO.getSortOrder();
			String searchQuery = flightInsuranceSearchDTO.getSearchQuery();
			Integer page = flightInsuranceSearchDTO.getPage();
			Integer size = flightInsuranceSearchDTO.getSize();

	        Specification<FlightInsurance> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, flightInsuranceId, "flightInsuranceId"); 
			

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

		Page<FlightInsurance> flightInsurances = this.getAllFlightInsurances(spec, pageable);
		
		//System.out.println(String.valueOf(flightInsurances.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(flightInsurances.getTotalPages()));
		
		List<FlightInsurance> flightInsurancesList = flightInsurances.getContent();
		
		FlightInsuranceConvertCriteriaDTO convertCriteria = new FlightInsuranceConvertCriteriaDTO();
		List<FlightInsuranceDTO> flightInsuranceDTOs = this.convertFlightInsurancesToFlightInsuranceDTOs(flightInsurancesList,convertCriteria);
		
		FlightInsurancePageDTO flightInsurancePageDTO = new FlightInsurancePageDTO();
		flightInsurancePageDTO.setFlightInsurances(flightInsuranceDTOs);
		flightInsurancePageDTO.setTotalElements(flightInsurances.getTotalElements());
		return ResponseEntity.ok(flightInsurancePageDTO);
	}

	public List<FlightInsuranceDTO> convertFlightInsurancesToFlightInsuranceDTOs(List<FlightInsurance> flightInsurances, FlightInsuranceConvertCriteriaDTO convertCriteria) {
		
		List<FlightInsuranceDTO> flightInsuranceDTOs = new ArrayList<FlightInsuranceDTO>();
		
		for (FlightInsurance flightInsurance : flightInsurances) {
			flightInsuranceDTOs.add(convertFlightInsuranceToFlightInsuranceDTO(flightInsurance,convertCriteria));
		}
		
		return flightInsuranceDTOs;

	}
	
	public FlightInsuranceDTO convertFlightInsuranceToFlightInsuranceDTO(FlightInsurance flightInsurance, FlightInsuranceConvertCriteriaDTO convertCriteria) {
		
		FlightInsuranceDTO flightInsuranceDTO = new FlightInsuranceDTO();
		
		flightInsuranceDTO.setFlightInsuranceId(flightInsurance.getFlightInsuranceId());

	

		
		return flightInsuranceDTO;
	}

	public ResultDTO updateFlightInsurance(FlightInsuranceDTO flightInsuranceDTO, RequestDTO requestDTO) {
		
		FlightInsurance flightInsurance = flightInsuranceDao.getById(flightInsuranceDTO.getFlightInsuranceId());

		flightInsurance.setFlightInsuranceId(ControllerUtils.setValue(flightInsurance.getFlightInsuranceId(), flightInsuranceDTO.getFlightInsuranceId()));



        flightInsurance = flightInsuranceDao.save(flightInsurance);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FlightInsuranceDTO getFlightInsuranceDTOById(Integer flightInsuranceId) {
	
		FlightInsurance flightInsurance = flightInsuranceDao.getById(flightInsuranceId);
			
		
		FlightInsuranceConvertCriteriaDTO convertCriteria = new FlightInsuranceConvertCriteriaDTO();
		return(this.convertFlightInsuranceToFlightInsuranceDTO(flightInsurance,convertCriteria));
	}







}
