package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.Insurance;
import com.skyspaceeleven.dto.InsuranceDTO;
import com.skyspaceeleven.dto.InsuranceSearchDTO;
import com.skyspaceeleven.dto.InsurancePageDTO;
import com.skyspaceeleven.dto.InsuranceConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InsuranceService extends GenericService<Insurance, Integer> {

	List<Insurance> findAll();

	ResultDTO addInsurance(InsuranceDTO insuranceDTO, RequestDTO requestDTO);

	ResultDTO updateInsurance(InsuranceDTO insuranceDTO, RequestDTO requestDTO);

    Page<Insurance> getAllInsurances(Pageable pageable);

    Page<Insurance> getAllInsurances(Specification<Insurance> spec, Pageable pageable);

	ResponseEntity<InsurancePageDTO> getInsurances(InsuranceSearchDTO insuranceSearchDTO);
	
	List<InsuranceDTO> convertInsurancesToInsuranceDTOs(List<Insurance> insurances, InsuranceConvertCriteriaDTO convertCriteria);

	InsuranceDTO getInsuranceDTOById(Integer insuranceId);







}





