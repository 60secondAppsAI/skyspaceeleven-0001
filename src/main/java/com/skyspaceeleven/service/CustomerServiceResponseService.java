package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.CustomerServiceResponse;
import com.skyspaceeleven.dto.CustomerServiceResponseDTO;
import com.skyspaceeleven.dto.CustomerServiceResponseSearchDTO;
import com.skyspaceeleven.dto.CustomerServiceResponsePageDTO;
import com.skyspaceeleven.dto.CustomerServiceResponseConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CustomerServiceResponseService extends GenericService<CustomerServiceResponse, Integer> {

	List<CustomerServiceResponse> findAll();

	ResultDTO addCustomerServiceResponse(CustomerServiceResponseDTO customerServiceResponseDTO, RequestDTO requestDTO);

	ResultDTO updateCustomerServiceResponse(CustomerServiceResponseDTO customerServiceResponseDTO, RequestDTO requestDTO);

    Page<CustomerServiceResponse> getAllCustomerServiceResponses(Pageable pageable);

    Page<CustomerServiceResponse> getAllCustomerServiceResponses(Specification<CustomerServiceResponse> spec, Pageable pageable);

	ResponseEntity<CustomerServiceResponsePageDTO> getCustomerServiceResponses(CustomerServiceResponseSearchDTO customerServiceResponseSearchDTO);
	
	List<CustomerServiceResponseDTO> convertCustomerServiceResponsesToCustomerServiceResponseDTOs(List<CustomerServiceResponse> customerServiceResponses, CustomerServiceResponseConvertCriteriaDTO convertCriteria);

	CustomerServiceResponseDTO getCustomerServiceResponseDTOById(Integer customerServiceResponseId);







}





