package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.Crew;
import com.skyspaceeleven.dto.CrewDTO;
import com.skyspaceeleven.dto.CrewSearchDTO;
import com.skyspaceeleven.dto.CrewPageDTO;
import com.skyspaceeleven.dto.CrewConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CrewService extends GenericService<Crew, Integer> {

	List<Crew> findAll();

	ResultDTO addCrew(CrewDTO crewDTO, RequestDTO requestDTO);

	ResultDTO updateCrew(CrewDTO crewDTO, RequestDTO requestDTO);

    Page<Crew> getAllCrews(Pageable pageable);

    Page<Crew> getAllCrews(Specification<Crew> spec, Pageable pageable);

	ResponseEntity<CrewPageDTO> getCrews(CrewSearchDTO crewSearchDTO);
	
	List<CrewDTO> convertCrewsToCrewDTOs(List<Crew> crews, CrewConvertCriteriaDTO convertCriteria);

	CrewDTO getCrewDTOById(Integer crewId);







}





