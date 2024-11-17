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
import com.skyspaceeleven.dao.ScheduledEventDAO;
import com.skyspaceeleven.domain.ScheduledEvent;
import com.skyspaceeleven.dto.ScheduledEventDTO;
import com.skyspaceeleven.dto.ScheduledEventSearchDTO;
import com.skyspaceeleven.dto.ScheduledEventPageDTO;
import com.skyspaceeleven.dto.ScheduledEventConvertCriteriaDTO;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import com.skyspaceeleven.service.ScheduledEventService;
import com.skyspaceeleven.util.ControllerUtils;





@Service
public class ScheduledEventServiceImpl extends GenericServiceImpl<ScheduledEvent, Integer> implements ScheduledEventService {

    private final static Logger logger = LoggerFactory.getLogger(ScheduledEventServiceImpl.class);

	@Autowired
	ScheduledEventDAO scheduledEventDao;

	


	@Override
	public GenericDAO<ScheduledEvent, Integer> getDAO() {
		return (GenericDAO<ScheduledEvent, Integer>) scheduledEventDao;
	}
	
	public List<ScheduledEvent> findAll () {
		List<ScheduledEvent> scheduledEvents = scheduledEventDao.findAll();
		
		return scheduledEvents;	
		
	}

	public ResultDTO addScheduledEvent(ScheduledEventDTO scheduledEventDTO, RequestDTO requestDTO) {

		ScheduledEvent scheduledEvent = new ScheduledEvent();

		scheduledEvent.setScheduledEventId(scheduledEventDTO.getScheduledEventId());


		scheduledEvent.setEventTime(scheduledEventDTO.getEventTime());


		scheduledEvent.setDescription(scheduledEventDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		scheduledEvent = scheduledEventDao.save(scheduledEvent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ScheduledEvent> getAllScheduledEvents(Pageable pageable) {
		return scheduledEventDao.findAll(pageable);
	}

	public Page<ScheduledEvent> getAllScheduledEvents(Specification<ScheduledEvent> spec, Pageable pageable) {
		return scheduledEventDao.findAll(spec, pageable);
	}

	public ResponseEntity<ScheduledEventPageDTO> getScheduledEvents(ScheduledEventSearchDTO scheduledEventSearchDTO) {
	
			Integer scheduledEventId = scheduledEventSearchDTO.getScheduledEventId(); 
   			String description = scheduledEventSearchDTO.getDescription(); 
 			String sortBy = scheduledEventSearchDTO.getSortBy();
			String sortOrder = scheduledEventSearchDTO.getSortOrder();
			String searchQuery = scheduledEventSearchDTO.getSearchQuery();
			Integer page = scheduledEventSearchDTO.getPage();
			Integer size = scheduledEventSearchDTO.getSize();

	        Specification<ScheduledEvent> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, scheduledEventId, "scheduledEventId"); 
			
 			
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

		Page<ScheduledEvent> scheduledEvents = this.getAllScheduledEvents(spec, pageable);
		
		//System.out.println(String.valueOf(scheduledEvents.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(scheduledEvents.getTotalPages()));
		
		List<ScheduledEvent> scheduledEventsList = scheduledEvents.getContent();
		
		ScheduledEventConvertCriteriaDTO convertCriteria = new ScheduledEventConvertCriteriaDTO();
		List<ScheduledEventDTO> scheduledEventDTOs = this.convertScheduledEventsToScheduledEventDTOs(scheduledEventsList,convertCriteria);
		
		ScheduledEventPageDTO scheduledEventPageDTO = new ScheduledEventPageDTO();
		scheduledEventPageDTO.setScheduledEvents(scheduledEventDTOs);
		scheduledEventPageDTO.setTotalElements(scheduledEvents.getTotalElements());
		return ResponseEntity.ok(scheduledEventPageDTO);
	}

	public List<ScheduledEventDTO> convertScheduledEventsToScheduledEventDTOs(List<ScheduledEvent> scheduledEvents, ScheduledEventConvertCriteriaDTO convertCriteria) {
		
		List<ScheduledEventDTO> scheduledEventDTOs = new ArrayList<ScheduledEventDTO>();
		
		for (ScheduledEvent scheduledEvent : scheduledEvents) {
			scheduledEventDTOs.add(convertScheduledEventToScheduledEventDTO(scheduledEvent,convertCriteria));
		}
		
		return scheduledEventDTOs;

	}
	
	public ScheduledEventDTO convertScheduledEventToScheduledEventDTO(ScheduledEvent scheduledEvent, ScheduledEventConvertCriteriaDTO convertCriteria) {
		
		ScheduledEventDTO scheduledEventDTO = new ScheduledEventDTO();
		
		scheduledEventDTO.setScheduledEventId(scheduledEvent.getScheduledEventId());

	
		scheduledEventDTO.setEventTime(scheduledEvent.getEventTime());

	
		scheduledEventDTO.setDescription(scheduledEvent.getDescription());

	

		
		return scheduledEventDTO;
	}

	public ResultDTO updateScheduledEvent(ScheduledEventDTO scheduledEventDTO, RequestDTO requestDTO) {
		
		ScheduledEvent scheduledEvent = scheduledEventDao.getById(scheduledEventDTO.getScheduledEventId());

		scheduledEvent.setScheduledEventId(ControllerUtils.setValue(scheduledEvent.getScheduledEventId(), scheduledEventDTO.getScheduledEventId()));

		scheduledEvent.setEventTime(ControllerUtils.setValue(scheduledEvent.getEventTime(), scheduledEventDTO.getEventTime()));

		scheduledEvent.setDescription(ControllerUtils.setValue(scheduledEvent.getDescription(), scheduledEventDTO.getDescription()));



        scheduledEvent = scheduledEventDao.save(scheduledEvent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ScheduledEventDTO getScheduledEventDTOById(Integer scheduledEventId) {
	
		ScheduledEvent scheduledEvent = scheduledEventDao.getById(scheduledEventId);
			
		
		ScheduledEventConvertCriteriaDTO convertCriteria = new ScheduledEventConvertCriteriaDTO();
		return(this.convertScheduledEventToScheduledEventDTO(scheduledEvent,convertCriteria));
	}







}
