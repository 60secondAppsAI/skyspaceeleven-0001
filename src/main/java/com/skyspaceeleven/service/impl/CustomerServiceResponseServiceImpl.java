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
import com.skyspaceeleven.dao.CustomerServiceResponseDAO;
import com.skyspaceeleven.domain.CustomerServiceResponse;
import com.skyspaceeleven.dto.CustomerServiceResponseDTO;
import com.skyspaceeleven.dto.CustomerServiceResponseSearchDTO;
import com.skyspaceeleven.dto.CustomerServiceResponsePageDTO;
import com.skyspaceeleven.dto.CustomerServiceResponseConvertCriteriaDTO;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import com.skyspaceeleven.service.CustomerServiceResponseService;
import com.skyspaceeleven.util.ControllerUtils;





@Service
public class CustomerServiceResponseServiceImpl extends GenericServiceImpl<CustomerServiceResponse, Integer> implements CustomerServiceResponseService {

    private final static Logger logger = LoggerFactory.getLogger(CustomerServiceResponseServiceImpl.class);

	@Autowired
	CustomerServiceResponseDAO customerServiceResponseDao;

	


	@Override
	public GenericDAO<CustomerServiceResponse, Integer> getDAO() {
		return (GenericDAO<CustomerServiceResponse, Integer>) customerServiceResponseDao;
	}
	
	public List<CustomerServiceResponse> findAll () {
		List<CustomerServiceResponse> customerServiceResponses = customerServiceResponseDao.findAll();
		
		return customerServiceResponses;	
		
	}

	public ResultDTO addCustomerServiceResponse(CustomerServiceResponseDTO customerServiceResponseDTO, RequestDTO requestDTO) {

		CustomerServiceResponse customerServiceResponse = new CustomerServiceResponse();

		customerServiceResponse.setCustomerServiceResponseId(customerServiceResponseDTO.getCustomerServiceResponseId());


		customerServiceResponse.setResponseDetail(customerServiceResponseDTO.getResponseDetail());


		customerServiceResponse.setResponseDate(customerServiceResponseDTO.getResponseDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		customerServiceResponse = customerServiceResponseDao.save(customerServiceResponse);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CustomerServiceResponse> getAllCustomerServiceResponses(Pageable pageable) {
		return customerServiceResponseDao.findAll(pageable);
	}

	public Page<CustomerServiceResponse> getAllCustomerServiceResponses(Specification<CustomerServiceResponse> spec, Pageable pageable) {
		return customerServiceResponseDao.findAll(spec, pageable);
	}

	public ResponseEntity<CustomerServiceResponsePageDTO> getCustomerServiceResponses(CustomerServiceResponseSearchDTO customerServiceResponseSearchDTO) {
	
			Integer customerServiceResponseId = customerServiceResponseSearchDTO.getCustomerServiceResponseId(); 
 			String responseDetail = customerServiceResponseSearchDTO.getResponseDetail(); 
   			String sortBy = customerServiceResponseSearchDTO.getSortBy();
			String sortOrder = customerServiceResponseSearchDTO.getSortOrder();
			String searchQuery = customerServiceResponseSearchDTO.getSearchQuery();
			Integer page = customerServiceResponseSearchDTO.getPage();
			Integer size = customerServiceResponseSearchDTO.getSize();

	        Specification<CustomerServiceResponse> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, customerServiceResponseId, "customerServiceResponseId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, responseDetail, "responseDetail"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("responseDetail")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CustomerServiceResponse> customerServiceResponses = this.getAllCustomerServiceResponses(spec, pageable);
		
		//System.out.println(String.valueOf(customerServiceResponses.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(customerServiceResponses.getTotalPages()));
		
		List<CustomerServiceResponse> customerServiceResponsesList = customerServiceResponses.getContent();
		
		CustomerServiceResponseConvertCriteriaDTO convertCriteria = new CustomerServiceResponseConvertCriteriaDTO();
		List<CustomerServiceResponseDTO> customerServiceResponseDTOs = this.convertCustomerServiceResponsesToCustomerServiceResponseDTOs(customerServiceResponsesList,convertCriteria);
		
		CustomerServiceResponsePageDTO customerServiceResponsePageDTO = new CustomerServiceResponsePageDTO();
		customerServiceResponsePageDTO.setCustomerServiceResponses(customerServiceResponseDTOs);
		customerServiceResponsePageDTO.setTotalElements(customerServiceResponses.getTotalElements());
		return ResponseEntity.ok(customerServiceResponsePageDTO);
	}

	public List<CustomerServiceResponseDTO> convertCustomerServiceResponsesToCustomerServiceResponseDTOs(List<CustomerServiceResponse> customerServiceResponses, CustomerServiceResponseConvertCriteriaDTO convertCriteria) {
		
		List<CustomerServiceResponseDTO> customerServiceResponseDTOs = new ArrayList<CustomerServiceResponseDTO>();
		
		for (CustomerServiceResponse customerServiceResponse : customerServiceResponses) {
			customerServiceResponseDTOs.add(convertCustomerServiceResponseToCustomerServiceResponseDTO(customerServiceResponse,convertCriteria));
		}
		
		return customerServiceResponseDTOs;

	}
	
	public CustomerServiceResponseDTO convertCustomerServiceResponseToCustomerServiceResponseDTO(CustomerServiceResponse customerServiceResponse, CustomerServiceResponseConvertCriteriaDTO convertCriteria) {
		
		CustomerServiceResponseDTO customerServiceResponseDTO = new CustomerServiceResponseDTO();
		
		customerServiceResponseDTO.setCustomerServiceResponseId(customerServiceResponse.getCustomerServiceResponseId());

	
		customerServiceResponseDTO.setResponseDetail(customerServiceResponse.getResponseDetail());

	
		customerServiceResponseDTO.setResponseDate(customerServiceResponse.getResponseDate());

	

		
		return customerServiceResponseDTO;
	}

	public ResultDTO updateCustomerServiceResponse(CustomerServiceResponseDTO customerServiceResponseDTO, RequestDTO requestDTO) {
		
		CustomerServiceResponse customerServiceResponse = customerServiceResponseDao.getById(customerServiceResponseDTO.getCustomerServiceResponseId());

		customerServiceResponse.setCustomerServiceResponseId(ControllerUtils.setValue(customerServiceResponse.getCustomerServiceResponseId(), customerServiceResponseDTO.getCustomerServiceResponseId()));

		customerServiceResponse.setResponseDetail(ControllerUtils.setValue(customerServiceResponse.getResponseDetail(), customerServiceResponseDTO.getResponseDetail()));

		customerServiceResponse.setResponseDate(ControllerUtils.setValue(customerServiceResponse.getResponseDate(), customerServiceResponseDTO.getResponseDate()));



        customerServiceResponse = customerServiceResponseDao.save(customerServiceResponse);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CustomerServiceResponseDTO getCustomerServiceResponseDTOById(Integer customerServiceResponseId) {
	
		CustomerServiceResponse customerServiceResponse = customerServiceResponseDao.getById(customerServiceResponseId);
			
		
		CustomerServiceResponseConvertCriteriaDTO convertCriteria = new CustomerServiceResponseConvertCriteriaDTO();
		return(this.convertCustomerServiceResponseToCustomerServiceResponseDTO(customerServiceResponse,convertCriteria));
	}







}
