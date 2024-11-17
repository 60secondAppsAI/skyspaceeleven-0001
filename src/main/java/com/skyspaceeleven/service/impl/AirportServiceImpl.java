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
import com.skyspaceeleven.dao.AirportDAO;
import com.skyspaceeleven.domain.Airport;
import com.skyspaceeleven.dto.AirportDTO;
import com.skyspaceeleven.dto.AirportSearchDTO;
import com.skyspaceeleven.dto.AirportPageDTO;
import com.skyspaceeleven.dto.AirportConvertCriteriaDTO;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import com.skyspaceeleven.service.AirportService;
import com.skyspaceeleven.util.ControllerUtils;





@Service
public class AirportServiceImpl extends GenericServiceImpl<Airport, Integer> implements AirportService {

    private final static Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);

	@Autowired
	AirportDAO airportDao;

	


	@Override
	public GenericDAO<Airport, Integer> getDAO() {
		return (GenericDAO<Airport, Integer>) airportDao;
	}
	
	public List<Airport> findAll () {
		List<Airport> airports = airportDao.findAll();
		
		return airports;	
		
	}

	public ResultDTO addAirport(AirportDTO airportDTO, RequestDTO requestDTO) {

		Airport airport = new Airport();

		airport.setAirportId(airportDTO.getAirportId());


		airport.setName(airportDTO.getName());


		airport.setCode(airportDTO.getCode());


		airport.setCity(airportDTO.getCity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		airport = airportDao.save(airport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Airport> getAllAirports(Pageable pageable) {
		return airportDao.findAll(pageable);
	}

	public Page<Airport> getAllAirports(Specification<Airport> spec, Pageable pageable) {
		return airportDao.findAll(spec, pageable);
	}

	public ResponseEntity<AirportPageDTO> getAirports(AirportSearchDTO airportSearchDTO) {
	
			Integer airportId = airportSearchDTO.getAirportId(); 
 			String name = airportSearchDTO.getName(); 
 			String code = airportSearchDTO.getCode(); 
 			String city = airportSearchDTO.getCity(); 
 			String sortBy = airportSearchDTO.getSortBy();
			String sortOrder = airportSearchDTO.getSortOrder();
			String searchQuery = airportSearchDTO.getSearchQuery();
			Integer page = airportSearchDTO.getPage();
			Integer size = airportSearchDTO.getSize();

	        Specification<Airport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, airportId, "airportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, code, "code"); 
			
			spec = ControllerUtils.andIfNecessary(spec, city, "city"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("code")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("city")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Airport> airports = this.getAllAirports(spec, pageable);
		
		//System.out.println(String.valueOf(airports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(airports.getTotalPages()));
		
		List<Airport> airportsList = airports.getContent();
		
		AirportConvertCriteriaDTO convertCriteria = new AirportConvertCriteriaDTO();
		List<AirportDTO> airportDTOs = this.convertAirportsToAirportDTOs(airportsList,convertCriteria);
		
		AirportPageDTO airportPageDTO = new AirportPageDTO();
		airportPageDTO.setAirports(airportDTOs);
		airportPageDTO.setTotalElements(airports.getTotalElements());
		return ResponseEntity.ok(airportPageDTO);
	}

	public List<AirportDTO> convertAirportsToAirportDTOs(List<Airport> airports, AirportConvertCriteriaDTO convertCriteria) {
		
		List<AirportDTO> airportDTOs = new ArrayList<AirportDTO>();
		
		for (Airport airport : airports) {
			airportDTOs.add(convertAirportToAirportDTO(airport,convertCriteria));
		}
		
		return airportDTOs;

	}
	
	public AirportDTO convertAirportToAirportDTO(Airport airport, AirportConvertCriteriaDTO convertCriteria) {
		
		AirportDTO airportDTO = new AirportDTO();
		
		airportDTO.setAirportId(airport.getAirportId());

	
		airportDTO.setName(airport.getName());

	
		airportDTO.setCode(airport.getCode());

	
		airportDTO.setCity(airport.getCity());

	

		
		return airportDTO;
	}

	public ResultDTO updateAirport(AirportDTO airportDTO, RequestDTO requestDTO) {
		
		Airport airport = airportDao.getById(airportDTO.getAirportId());

		airport.setAirportId(ControllerUtils.setValue(airport.getAirportId(), airportDTO.getAirportId()));

		airport.setName(ControllerUtils.setValue(airport.getName(), airportDTO.getName()));

		airport.setCode(ControllerUtils.setValue(airport.getCode(), airportDTO.getCode()));

		airport.setCity(ControllerUtils.setValue(airport.getCity(), airportDTO.getCity()));



        airport = airportDao.save(airport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AirportDTO getAirportDTOById(Integer airportId) {
	
		Airport airport = airportDao.getById(airportId);
			
		
		AirportConvertCriteriaDTO convertCriteria = new AirportConvertCriteriaDTO();
		return(this.convertAirportToAirportDTO(airport,convertCriteria));
	}







}
