package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.FlightCrew;
import com.skyspaceeleven.dto.FlightCrewDTO;
import com.skyspaceeleven.dto.FlightCrewSearchDTO;
import com.skyspaceeleven.dto.FlightCrewPageDTO;
import com.skyspaceeleven.dto.FlightCrewConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightCrewService extends GenericService<FlightCrew, Integer> {

	List<FlightCrew> findAll();

	ResultDTO addFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO);

	ResultDTO updateFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO);

    Page<FlightCrew> getAllFlightCrews(Pageable pageable);

    Page<FlightCrew> getAllFlightCrews(Specification<FlightCrew> spec, Pageable pageable);

	ResponseEntity<FlightCrewPageDTO> getFlightCrews(FlightCrewSearchDTO flightCrewSearchDTO);
	
	List<FlightCrewDTO> convertFlightCrewsToFlightCrewDTOs(List<FlightCrew> flightCrews, FlightCrewConvertCriteriaDTO convertCriteria);

	FlightCrewDTO getFlightCrewDTOById(Integer flightCrewId);







}





