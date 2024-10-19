package com.base.basesetup.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostDebitNoteDTO {

	private Long id;
	private String branch;
	private String branchCode;
	private String createdBy;
	private String cancelRemarks;
	private String finYear;
	private String screenCode;
	private String screenName;
	private String ipNo;
	private String latitude;
	private Long orgId;
	private String docNo;
	private String subType;
	private String product;
	private String vohNo;
	private String vohDate; 
	private String partyType;
	private String suppRefNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate suppDate;
	private String partyName;
	private String partyCode;
	private int creditDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dueDate;
	private String address;
	private String currency;
	private double exRate;
	private String otherInfo;
	private String remarks;
	private String shipRefNo;
	private String status;
	private String orginBill;
	private String gstType;
	
	private List<CostDebitNoteSummaryDTO> costDebitNoteSummaryDTO;
	
	private List<CostDebitChargesDTO> costDebitChargesDTO;
	
	private List<CostDebitNoteGstDTO> costDebitNoteGstDTO;
	
	private List<CostDebitNoteTaxPrtculDTO> costDebitNoteTaxPrtculDTO;
	
	
}
