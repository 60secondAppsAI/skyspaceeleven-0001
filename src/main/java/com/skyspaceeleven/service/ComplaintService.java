package com.skyspaceeleven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeleven.domain.Complaint;
import com.skyspaceeleven.dto.ComplaintDTO;
import com.skyspaceeleven.dto.ComplaintSearchDTO;
import com.skyspaceeleven.dto.ComplaintPageDTO;
import com.skyspaceeleven.dto.ComplaintConvertCriteriaDTO;
import com.skyspaceeleven.service.GenericService;
import com.skyspaceeleven.dto.common.RequestDTO;
import com.skyspaceeleven.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ComplaintService extends GenericService<Complaint, Integer> {

	List<Complaint> findAll();

	ResultDTO addComplaint(ComplaintDTO complaintDTO, RequestDTO requestDTO);

	ResultDTO updateComplaint(ComplaintDTO complaintDTO, RequestDTO requestDTO);

    Page<Complaint> getAllComplaints(Pageable pageable);

    Page<Complaint> getAllComplaints(Specification<Complaint> spec, Pageable pageable);

	ResponseEntity<ComplaintPageDTO> getComplaints(ComplaintSearchDTO complaintSearchDTO);
	
	List<ComplaintDTO> convertComplaintsToComplaintDTOs(List<Complaint> complaints, ComplaintConvertCriteriaDTO convertCriteria);

	ComplaintDTO getComplaintDTOById(Integer complaintId);







}





