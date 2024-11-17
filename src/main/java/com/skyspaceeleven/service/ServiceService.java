package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.Service;
import com.skyspaceeleven.dto.ServiceDTO;
import com.skyspaceeleven.dto.ServiceSearchDTO;
import com.skyspaceeleven.dto.ServicePageDTO;
import com.skyspaceeleven.dto.ServiceConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ServiceService extends GenericService<Service, Integer> {

	List<Service> findAll();

	ResultDTO addService(ServiceDTO serviceDTO, RequestDTO requestDTO);

	ResultDTO updateService(ServiceDTO serviceDTO, RequestDTO requestDTO);

    Page<Service> getAllServices(Pageable pageable);

    Page<Service> getAllServices(Specification<Service> spec, Pageable pageable);

	ResponseEntity<ServicePageDTO> getServices(ServiceSearchDTO serviceSearchDTO);
	
	List<ServiceDTO> convertServicesToServiceDTOs(List<Service> services, ServiceConvertCriteriaDTO convertCriteria);

	ServiceDTO getServiceDTOById(Integer serviceId);







}





