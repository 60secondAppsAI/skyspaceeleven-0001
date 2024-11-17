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
import com.skyspaceeleven.dao.BookingAgentDAO;
import com.skyspaceeleven.domain.BookingAgent;
import com.skyspaceeleven.dto.BookingAgentDTO;
import com.skyspaceeleven.dto.BookingAgentSearchDTO;
import com.skyspaceeleven.dto.BookingAgentPageDTO;
import com.skyspaceeleven.dto.BookingAgentConvertCriteriaDTO;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import com.skyspaceeleven.service.BookingAgentService;
import com.skyspaceeleven.util.ControllerUtils;





@Service
public class BookingAgentServiceImpl extends GenericServiceImpl<BookingAgent, Integer> implements BookingAgentService {

    private final static Logger logger = LoggerFactory.getLogger(BookingAgentServiceImpl.class);

	@Autowired
	BookingAgentDAO bookingAgentDao;

	


	@Override
	public GenericDAO<BookingAgent, Integer> getDAO() {
		return (GenericDAO<BookingAgent, Integer>) bookingAgentDao;
	}
	
	public List<BookingAgent> findAll () {
		List<BookingAgent> bookingAgents = bookingAgentDao.findAll();
		
		return bookingAgents;	
		
	}

	public ResultDTO addBookingAgent(BookingAgentDTO bookingAgentDTO, RequestDTO requestDTO) {

		BookingAgent bookingAgent = new BookingAgent();

		bookingAgent.setBookingAgentId(bookingAgentDTO.getBookingAgentId());


		bookingAgent.setName(bookingAgentDTO.getName());


		bookingAgent.setAgencyName(bookingAgentDTO.getAgencyName());


		bookingAgent.setContactEmail(bookingAgentDTO.getContactEmail());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		bookingAgent = bookingAgentDao.save(bookingAgent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<BookingAgent> getAllBookingAgents(Pageable pageable) {
		return bookingAgentDao.findAll(pageable);
	}

	public Page<BookingAgent> getAllBookingAgents(Specification<BookingAgent> spec, Pageable pageable) {
		return bookingAgentDao.findAll(spec, pageable);
	}

	public ResponseEntity<BookingAgentPageDTO> getBookingAgents(BookingAgentSearchDTO bookingAgentSearchDTO) {
	
			Integer bookingAgentId = bookingAgentSearchDTO.getBookingAgentId(); 
 			String name = bookingAgentSearchDTO.getName(); 
 			String agencyName = bookingAgentSearchDTO.getAgencyName(); 
 			String contactEmail = bookingAgentSearchDTO.getContactEmail(); 
 			String sortBy = bookingAgentSearchDTO.getSortBy();
			String sortOrder = bookingAgentSearchDTO.getSortOrder();
			String searchQuery = bookingAgentSearchDTO.getSearchQuery();
			Integer page = bookingAgentSearchDTO.getPage();
			Integer size = bookingAgentSearchDTO.getSize();

	        Specification<BookingAgent> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, bookingAgentId, "bookingAgentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, agencyName, "agencyName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactEmail, "contactEmail"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("agencyName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactEmail")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<BookingAgent> bookingAgents = this.getAllBookingAgents(spec, pageable);
		
		//System.out.println(String.valueOf(bookingAgents.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(bookingAgents.getTotalPages()));
		
		List<BookingAgent> bookingAgentsList = bookingAgents.getContent();
		
		BookingAgentConvertCriteriaDTO convertCriteria = new BookingAgentConvertCriteriaDTO();
		List<BookingAgentDTO> bookingAgentDTOs = this.convertBookingAgentsToBookingAgentDTOs(bookingAgentsList,convertCriteria);
		
		BookingAgentPageDTO bookingAgentPageDTO = new BookingAgentPageDTO();
		bookingAgentPageDTO.setBookingAgents(bookingAgentDTOs);
		bookingAgentPageDTO.setTotalElements(bookingAgents.getTotalElements());
		return ResponseEntity.ok(bookingAgentPageDTO);
	}

	public List<BookingAgentDTO> convertBookingAgentsToBookingAgentDTOs(List<BookingAgent> bookingAgents, BookingAgentConvertCriteriaDTO convertCriteria) {
		
		List<BookingAgentDTO> bookingAgentDTOs = new ArrayList<BookingAgentDTO>();
		
		for (BookingAgent bookingAgent : bookingAgents) {
			bookingAgentDTOs.add(convertBookingAgentToBookingAgentDTO(bookingAgent,convertCriteria));
		}
		
		return bookingAgentDTOs;

	}
	
	public BookingAgentDTO convertBookingAgentToBookingAgentDTO(BookingAgent bookingAgent, BookingAgentConvertCriteriaDTO convertCriteria) {
		
		BookingAgentDTO bookingAgentDTO = new BookingAgentDTO();
		
		bookingAgentDTO.setBookingAgentId(bookingAgent.getBookingAgentId());

	
		bookingAgentDTO.setName(bookingAgent.getName());

	
		bookingAgentDTO.setAgencyName(bookingAgent.getAgencyName());

	
		bookingAgentDTO.setContactEmail(bookingAgent.getContactEmail());

	

		
		return bookingAgentDTO;
	}

	public ResultDTO updateBookingAgent(BookingAgentDTO bookingAgentDTO, RequestDTO requestDTO) {
		
		BookingAgent bookingAgent = bookingAgentDao.getById(bookingAgentDTO.getBookingAgentId());

		bookingAgent.setBookingAgentId(ControllerUtils.setValue(bookingAgent.getBookingAgentId(), bookingAgentDTO.getBookingAgentId()));

		bookingAgent.setName(ControllerUtils.setValue(bookingAgent.getName(), bookingAgentDTO.getName()));

		bookingAgent.setAgencyName(ControllerUtils.setValue(bookingAgent.getAgencyName(), bookingAgentDTO.getAgencyName()));

		bookingAgent.setContactEmail(ControllerUtils.setValue(bookingAgent.getContactEmail(), bookingAgentDTO.getContactEmail()));



        bookingAgent = bookingAgentDao.save(bookingAgent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BookingAgentDTO getBookingAgentDTOById(Integer bookingAgentId) {
	
		BookingAgent bookingAgent = bookingAgentDao.getById(bookingAgentId);
			
		
		BookingAgentConvertCriteriaDTO convertCriteria = new BookingAgentConvertCriteriaDTO();
		return(this.convertBookingAgentToBookingAgentDTO(bookingAgent,convertCriteria));
	}







}
