package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.FlightInsurance;
import com.skyspaceeleven.dto.FlightInsuranceDTO;
import com.skyspaceeleven.dto.FlightInsuranceSearchDTO;
import com.skyspaceeleven.dto.FlightInsurancePageDTO;
import com.skyspaceeleven.dto.FlightInsuranceConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightInsuranceService extends GenericService<FlightInsurance, Integer> {

	List<FlightInsurance> findAll();

	ResultDTO addFlightInsurance(FlightInsuranceDTO flightInsuranceDTO, RequestDTO requestDTO);

	ResultDTO updateFlightInsurance(FlightInsuranceDTO flightInsuranceDTO, RequestDTO requestDTO);

    Page<FlightInsurance> getAllFlightInsurances(Pageable pageable);

    Page<FlightInsurance> getAllFlightInsurances(Specification<FlightInsurance> spec, Pageable pageable);

	ResponseEntity<FlightInsurancePageDTO> getFlightInsurances(FlightInsuranceSearchDTO flightInsuranceSearchDTO);
	
	List<FlightInsuranceDTO> convertFlightInsurancesToFlightInsuranceDTOs(List<FlightInsurance> flightInsurances, FlightInsuranceConvertCriteriaDTO convertCriteria);

	FlightInsuranceDTO getFlightInsuranceDTOById(Integer flightInsuranceId);







}





