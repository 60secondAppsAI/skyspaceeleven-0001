package com.skyspaceeleven.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skyspaceeleven.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skyspaceeleven.domain.FlightInsurance;
import com.skyspaceeleven.dto.FlightInsuranceDTO;
import com.skyspaceeleven.dto.FlightInsuranceSearchDTO;
import com.skyspaceeleven.dto.FlightInsurancePageDTO;
import com.skyspaceeleven.service.FlightInsuranceService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/flightInsurance")
@RestController
public class FlightInsuranceController {

	private final static Logger logger = LoggerFactory.getLogger(FlightInsuranceController.class);

	@Autowired
	FlightInsuranceService flightInsuranceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<FlightInsurance> getAll() {

		List<FlightInsurance> flightInsurances = flightInsuranceService.findAll();
		
		return flightInsurances;	
	}

	@GetMapping(value = "/{flightInsuranceId}")
	@ResponseBody
	public FlightInsuranceDTO getFlightInsurance(@PathVariable Integer flightInsuranceId) {
		
		return (flightInsuranceService.getFlightInsuranceDTOById(flightInsuranceId));
	}

 	@RequestMapping(value = "/addFlightInsurance", method = RequestMethod.POST)
	public ResponseEntity<?> addFlightInsurance(@RequestBody FlightInsuranceDTO flightInsuranceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightInsuranceService.addFlightInsurance(flightInsuranceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/flightInsurances")
	public ResponseEntity<FlightInsurancePageDTO> getFlightInsurances(FlightInsuranceSearchDTO flightInsuranceSearchDTO) {
 
		return flightInsuranceService.getFlightInsurances(flightInsuranceSearchDTO);
	}	

	@RequestMapping(value = "/updateFlightInsurance", method = RequestMethod.POST)
	public ResponseEntity<?> updateFlightInsurance(@RequestBody FlightInsuranceDTO flightInsuranceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightInsuranceService.updateFlightInsurance(flightInsuranceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
