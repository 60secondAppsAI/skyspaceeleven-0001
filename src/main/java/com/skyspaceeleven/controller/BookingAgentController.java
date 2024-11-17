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

import com.skyspaceeleven.domain.BookingAgent;
import com.skyspaceeleven.dto.BookingAgentDTO;
import com.skyspaceeleven.dto.BookingAgentSearchDTO;
import com.skyspaceeleven.dto.BookingAgentPageDTO;
import com.skyspaceeleven.service.BookingAgentService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/bookingAgent")
@RestController
public class BookingAgentController {

	private final static Logger logger = LoggerFactory.getLogger(BookingAgentController.class);

	@Autowired
	BookingAgentService bookingAgentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<BookingAgent> getAll() {

		List<BookingAgent> bookingAgents = bookingAgentService.findAll();
		
		return bookingAgents;	
	}

	@GetMapping(value = "/{bookingAgentId}")
	@ResponseBody
	public BookingAgentDTO getBookingAgent(@PathVariable Integer bookingAgentId) {
		
		return (bookingAgentService.getBookingAgentDTOById(bookingAgentId));
	}

 	@RequestMapping(value = "/addBookingAgent", method = RequestMethod.POST)
	public ResponseEntity<?> addBookingAgent(@RequestBody BookingAgentDTO bookingAgentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingAgentService.addBookingAgent(bookingAgentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/bookingAgents")
	public ResponseEntity<BookingAgentPageDTO> getBookingAgents(BookingAgentSearchDTO bookingAgentSearchDTO) {
 
		return bookingAgentService.getBookingAgents(bookingAgentSearchDTO);
	}	

	@RequestMapping(value = "/updateBookingAgent", method = RequestMethod.POST)
	public ResponseEntity<?> updateBookingAgent(@RequestBody BookingAgentDTO bookingAgentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingAgentService.updateBookingAgent(bookingAgentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
