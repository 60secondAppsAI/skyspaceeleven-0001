package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.Membership;
import com.skyspaceeleven.dto.MembershipDTO;
import com.skyspaceeleven.dto.MembershipSearchDTO;
import com.skyspaceeleven.dto.MembershipPageDTO;
import com.skyspaceeleven.dto.MembershipConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MembershipService extends GenericService<Membership, Integer> {

	List<Membership> findAll();

	ResultDTO addMembership(MembershipDTO membershipDTO, RequestDTO requestDTO);

	ResultDTO updateMembership(MembershipDTO membershipDTO, RequestDTO requestDTO);

    Page<Membership> getAllMemberships(Pageable pageable);

    Page<Membership> getAllMemberships(Specification<Membership> spec, Pageable pageable);

	ResponseEntity<MembershipPageDTO> getMemberships(MembershipSearchDTO membershipSearchDTO);
	
	List<MembershipDTO> convertMembershipsToMembershipDTOs(List<Membership> memberships, MembershipConvertCriteriaDTO convertCriteria);

	MembershipDTO getMembershipDTOById(Integer membershipId);







}





