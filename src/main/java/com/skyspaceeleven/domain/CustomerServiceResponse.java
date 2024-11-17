package com.skyspaceeleven.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="customer_service_responses")
@Getter @Setter @NoArgsConstructor
public class CustomerServiceResponse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="customer_service_response_id")
	private Integer customerServiceResponseId;
    
  	@Column(name="response_detail")
	private String responseDetail;
    
  	@Column(name="response_date")
	private Date responseDate;
    
	




}
