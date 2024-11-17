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
import com.skyspaceeleven.dao.ServiceDAO;
import com.skyspaceeleven.domain.Service;
import com.skyspaceeleven.dto.ServiceDTO;
import com.skyspaceeleven.dto.ServiceSearchDTO;
import com.skyspaceeleven.dto.ServicePageDTO;
import com.skyspaceeleven.dto.ServiceConvertCriteriaDTO;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import com.skyspaceeleven.service.ServiceService;
import com.skyspaceeleven.util.ControllerUtils;





@Service
public class ServiceServiceImpl extends GenericServiceImpl<Service, Integer> implements ServiceService {

    private final static Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

	@Autowired
	ServiceDAO serviceDao;

	


	@Override
	public GenericDAO<Service, Integer> getDAO() {
		return (GenericDAO<Service, Integer>) serviceDao;
	}
	
	public List<Service> findAll () {
		List<Service> services = serviceDao.findAll();
		
		return services;	
		
	}

	public ResultDTO addService(ServiceDTO serviceDTO, RequestDTO requestDTO) {

		Service service = new Service();

		service.setServiceId(serviceDTO.getServiceId());


		service.setDescription(serviceDTO.getDescription());


		service.setPrice(serviceDTO.getPrice());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		service = serviceDao.save(service);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Service> getAllServices(Pageable pageable) {
		return serviceDao.findAll(pageable);
	}

	public Page<Service> getAllServices(Specification<Service> spec, Pageable pageable) {
		return serviceDao.findAll(spec, pageable);
	}

	public ResponseEntity<ServicePageDTO> getServices(ServiceSearchDTO serviceSearchDTO) {
	
			Integer serviceId = serviceSearchDTO.getServiceId(); 
 			String description = serviceSearchDTO.getDescription(); 
  			String sortBy = serviceSearchDTO.getSortBy();
			String sortOrder = serviceSearchDTO.getSortOrder();
			String searchQuery = serviceSearchDTO.getSearchQuery();
			Integer page = serviceSearchDTO.getPage();
			Integer size = serviceSearchDTO.getSize();

	        Specification<Service> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, serviceId, "serviceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Service> services = this.getAllServices(spec, pageable);
		
		//System.out.println(String.valueOf(services.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(services.getTotalPages()));
		
		List<Service> servicesList = services.getContent();
		
		ServiceConvertCriteriaDTO convertCriteria = new ServiceConvertCriteriaDTO();
		List<ServiceDTO> serviceDTOs = this.convertServicesToServiceDTOs(servicesList,convertCriteria);
		
		ServicePageDTO servicePageDTO = new ServicePageDTO();
		servicePageDTO.setServices(serviceDTOs);
		servicePageDTO.setTotalElements(services.getTotalElements());
		return ResponseEntity.ok(servicePageDTO);
	}

	public List<ServiceDTO> convertServicesToServiceDTOs(List<Service> services, ServiceConvertCriteriaDTO convertCriteria) {
		
		List<ServiceDTO> serviceDTOs = new ArrayList<ServiceDTO>();
		
		for (Service service : services) {
			serviceDTOs.add(convertServiceToServiceDTO(service,convertCriteria));
		}
		
		return serviceDTOs;

	}
	
	public ServiceDTO convertServiceToServiceDTO(Service service, ServiceConvertCriteriaDTO convertCriteria) {
		
		ServiceDTO serviceDTO = new ServiceDTO();
		
		serviceDTO.setServiceId(service.getServiceId());

	
		serviceDTO.setDescription(service.getDescription());

	
		serviceDTO.setPrice(service.getPrice());

	

		
		return serviceDTO;
	}

	public ResultDTO updateService(ServiceDTO serviceDTO, RequestDTO requestDTO) {
		
		Service service = serviceDao.getById(serviceDTO.getServiceId());

		service.setServiceId(ControllerUtils.setValue(service.getServiceId(), serviceDTO.getServiceId()));

		service.setDescription(ControllerUtils.setValue(service.getDescription(), serviceDTO.getDescription()));

		service.setPrice(ControllerUtils.setValue(service.getPrice(), serviceDTO.getPrice()));



        service = serviceDao.save(service);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ServiceDTO getServiceDTOById(Integer serviceId) {
	
		Service service = serviceDao.getById(serviceId);
			
		
		ServiceConvertCriteriaDTO convertCriteria = new ServiceConvertCriteriaDTO();
		return(this.convertServiceToServiceDTO(service,convertCriteria));
	}







}
