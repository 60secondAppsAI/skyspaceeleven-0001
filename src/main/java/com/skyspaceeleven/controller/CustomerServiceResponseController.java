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

import com.skyspaceeleven.domain.CustomerServiceResponse;
import com.skyspaceeleven.dto.CustomerServiceResponseDTO;
import com.skyspaceeleven.dto.CustomerServiceResponseSearchDTO;
import com.skyspaceeleven.dto.CustomerServiceResponsePageDTO;
import com.skyspaceeleven.service.CustomerServiceResponseService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/customerServiceResponse")
@RestController
public class CustomerServiceResponseController {

	private final static Logger logger = LoggerFactory.getLogger(CustomerServiceResponseController.class);

	@Autowired
	CustomerServiceResponseService customerServiceResponseService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CustomerServiceResponse> getAll() {

		List<CustomerServiceResponse> customerServiceResponses = customerServiceResponseService.findAll();
		
		return customerServiceResponses;	
	}

	@GetMapping(value = "/{customerServiceResponseId}")
	@ResponseBody
	public CustomerServiceResponseDTO getCustomerServiceResponse(@PathVariable Integer customerServiceResponseId) {
		
		return (customerServiceResponseService.getCustomerServiceResponseDTOById(customerServiceResponseId));
	}

 	@RequestMapping(value = "/addCustomerServiceResponse", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomerServiceResponse(@RequestBody CustomerServiceResponseDTO customerServiceResponseDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerServiceResponseService.addCustomerServiceResponse(customerServiceResponseDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/customerServiceResponses")
	public ResponseEntity<CustomerServiceResponsePageDTO> getCustomerServiceResponses(CustomerServiceResponseSearchDTO customerServiceResponseSearchDTO) {
 
		return customerServiceResponseService.getCustomerServiceResponses(customerServiceResponseSearchDTO);
	}	

	@RequestMapping(value = "/updateCustomerServiceResponse", method = RequestMethod.POST)
	public ResponseEntity<?> updateCustomerServiceResponse(@RequestBody CustomerServiceResponseDTO customerServiceResponseDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerServiceResponseService.updateCustomerServiceResponse(customerServiceResponseDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
