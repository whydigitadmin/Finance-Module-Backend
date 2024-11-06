package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TmsJobCardDTO {
	private Long id;
	private String jobNo;
	private String customer;
	private boolean operationClosed;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate date;
	private String salesCategory;
	private boolean financeClosed;
	private String salesPerson;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private  LocalDate closedOn;
	private BigDecimal income;
	private BigDecimal profit;
	private BigDecimal expense;
	private String remarks;
	private boolean closed;

	private Long orgId;
	private String branch;
	private String branchCode;
	private String createdBy;
	private boolean active;
	private String finyear;
	private String cancelRemarks;
	

	List<CostCenterTmsJobCardDTO> costCenterTmsJobCardDTO;

}
