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

import com.skyspaceeleven.domain.ScheduledEvent;
import com.skyspaceeleven.dto.ScheduledEventDTO;
import com.skyspaceeleven.dto.ScheduledEventSearchDTO;
import com.skyspaceeleven.dto.ScheduledEventPageDTO;
import com.skyspaceeleven.service.ScheduledEventService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/scheduledEvent")
@RestController
public class ScheduledEventController {

	private final static Logger logger = LoggerFactory.getLogger(ScheduledEventController.class);

	@Autowired
	ScheduledEventService scheduledEventService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ScheduledEvent> getAll() {

		List<ScheduledEvent> scheduledEvents = scheduledEventService.findAll();
		
		return scheduledEvents;	
	}

	@GetMapping(value = "/{scheduledEventId}")
	@ResponseBody
	public ScheduledEventDTO getScheduledEvent(@PathVariable Integer scheduledEventId) {
		
		return (scheduledEventService.getScheduledEventDTOById(scheduledEventId));
	}

 	@RequestMapping(value = "/addScheduledEvent", method = RequestMethod.POST)
	public ResponseEntity<?> addScheduledEvent(@RequestBody ScheduledEventDTO scheduledEventDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = scheduledEventService.addScheduledEvent(scheduledEventDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/scheduledEvents")
	public ResponseEntity<ScheduledEventPageDTO> getScheduledEvents(ScheduledEventSearchDTO scheduledEventSearchDTO) {
 
		return scheduledEventService.getScheduledEvents(scheduledEventSearchDTO);
	}	

	@RequestMapping(value = "/updateScheduledEvent", method = RequestMethod.POST)
	public ResponseEntity<?> updateScheduledEvent(@RequestBody ScheduledEventDTO scheduledEventDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = scheduledEventService.updateScheduledEvent(scheduledEventDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
