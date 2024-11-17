package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.ScheduledEvent;
import com.skyspaceeleven.dto.ScheduledEventDTO;
import com.skyspaceeleven.dto.ScheduledEventSearchDTO;
import com.skyspaceeleven.dto.ScheduledEventPageDTO;
import com.skyspaceeleven.dto.ScheduledEventConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ScheduledEventService extends GenericService<ScheduledEvent, Integer> {

	List<ScheduledEvent> findAll();

	ResultDTO addScheduledEvent(ScheduledEventDTO scheduledEventDTO, RequestDTO requestDTO);

	ResultDTO updateScheduledEvent(ScheduledEventDTO scheduledEventDTO, RequestDTO requestDTO);

    Page<ScheduledEvent> getAllScheduledEvents(Pageable pageable);

    Page<ScheduledEvent> getAllScheduledEvents(Specification<ScheduledEvent> spec, Pageable pageable);

	ResponseEntity<ScheduledEventPageDTO> getScheduledEvents(ScheduledEventSearchDTO scheduledEventSearchDTO);
	
	List<ScheduledEventDTO> convertScheduledEventsToScheduledEventDTOs(List<ScheduledEvent> scheduledEvents, ScheduledEventConvertCriteriaDTO convertCriteria);

	ScheduledEventDTO getScheduledEventDTOById(Integer scheduledEventId);







}





