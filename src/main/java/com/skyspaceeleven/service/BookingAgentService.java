package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.BookingAgent;
import com.skyspaceeleven.dto.BookingAgentDTO;
import com.skyspaceeleven.dto.BookingAgentSearchDTO;
import com.skyspaceeleven.dto.BookingAgentPageDTO;
import com.skyspaceeleven.dto.BookingAgentConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BookingAgentService extends GenericService<BookingAgent, Integer> {

	List<BookingAgent> findAll();

	ResultDTO addBookingAgent(BookingAgentDTO bookingAgentDTO, RequestDTO requestDTO);

	ResultDTO updateBookingAgent(BookingAgentDTO bookingAgentDTO, RequestDTO requestDTO);

    Page<BookingAgent> getAllBookingAgents(Pageable pageable);

    Page<BookingAgent> getAllBookingAgents(Specification<BookingAgent> spec, Pageable pageable);

	ResponseEntity<BookingAgentPageDTO> getBookingAgents(BookingAgentSearchDTO bookingAgentSearchDTO);
	
	List<BookingAgentDTO> convertBookingAgentsToBookingAgentDTOs(List<BookingAgent> bookingAgents, BookingAgentConvertCriteriaDTO convertCriteria);

	BookingAgentDTO getBookingAgentDTOById(Integer bookingAgentId);







}





