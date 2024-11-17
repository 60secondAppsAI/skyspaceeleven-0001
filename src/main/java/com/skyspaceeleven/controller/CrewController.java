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

import com.skyspaceeleven.domain.Crew;
import com.skyspaceeleven.dto.CrewDTO;
import com.skyspaceeleven.dto.CrewSearchDTO;
import com.skyspaceeleven.dto.CrewPageDTO;
import com.skyspaceeleven.service.CrewService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/crew")
@RestController
public class CrewController {

	private final static Logger logger = LoggerFactory.getLogger(CrewController.class);

	@Autowired
	CrewService crewService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Crew> getAll() {

		List<Crew> crews = crewService.findAll();
		
		return crews;	
	}

	@GetMapping(value = "/{crewId}")
	@ResponseBody
	public CrewDTO getCrew(@PathVariable Integer crewId) {
		
		return (crewService.getCrewDTOById(crewId));
	}

 	@RequestMapping(value = "/addCrew", method = RequestMethod.POST)
	public ResponseEntity<?> addCrew(@RequestBody CrewDTO crewDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = crewService.addCrew(crewDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/crews")
	public ResponseEntity<CrewPageDTO> getCrews(CrewSearchDTO crewSearchDTO) {
 
		return crewService.getCrews(crewSearchDTO);
	}	

	@RequestMapping(value = "/updateCrew", method = RequestMethod.POST)
	public ResponseEntity<?> updateCrew(@RequestBody CrewDTO crewDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = crewService.updateCrew(crewDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
