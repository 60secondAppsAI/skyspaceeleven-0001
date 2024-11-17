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

import com.skyspaceeleven.domain.LoyaltyProgram;
import com.skyspaceeleven.dto.LoyaltyProgramDTO;
import com.skyspaceeleven.dto.LoyaltyProgramSearchDTO;
import com.skyspaceeleven.dto.LoyaltyProgramPageDTO;
import com.skyspaceeleven.service.LoyaltyProgramService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/loyaltyProgram")
@RestController
public class LoyaltyProgramController {

	private final static Logger logger = LoggerFactory.getLogger(LoyaltyProgramController.class);

	@Autowired
	LoyaltyProgramService loyaltyProgramService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<LoyaltyProgram> getAll() {

		List<LoyaltyProgram> loyaltyPrograms = loyaltyProgramService.findAll();
		
		return loyaltyPrograms;	
	}

	@GetMapping(value = "/{loyaltyProgramId}")
	@ResponseBody
	public LoyaltyProgramDTO getLoyaltyProgram(@PathVariable Integer loyaltyProgramId) {
		
		return (loyaltyProgramService.getLoyaltyProgramDTOById(loyaltyProgramId));
	}

 	@RequestMapping(value = "/addLoyaltyProgram", method = RequestMethod.POST)
	public ResponseEntity<?> addLoyaltyProgram(@RequestBody LoyaltyProgramDTO loyaltyProgramDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loyaltyProgramService.addLoyaltyProgram(loyaltyProgramDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/loyaltyPrograms")
	public ResponseEntity<LoyaltyProgramPageDTO> getLoyaltyPrograms(LoyaltyProgramSearchDTO loyaltyProgramSearchDTO) {
 
		return loyaltyProgramService.getLoyaltyPrograms(loyaltyProgramSearchDTO);
	}	

	@RequestMapping(value = "/updateLoyaltyProgram", method = RequestMethod.POST)
	public ResponseEntity<?> updateLoyaltyProgram(@RequestBody LoyaltyProgramDTO loyaltyProgramDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loyaltyProgramService.updateLoyaltyProgram(loyaltyProgramDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
