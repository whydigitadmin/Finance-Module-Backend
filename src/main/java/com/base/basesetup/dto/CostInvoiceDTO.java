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
public class CostInvoiceDTO {

	private Long id;
	private String mode;
	private String product;
	private String purVoucherNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate purVoucherDate;
	private String costInvoiceNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate costInvoiceDate;
	private String supplierBillNo;
	private String suppliertType;
	private String supplierCode;
	private int creditDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dueDate;
	private String supplierName;
	private String supplierPlace;
	private String currency;
	private float exRate;
	private String supplierGstIn;
	private String supplierGstInCode;
	private String remarks;
	private String address;
	private String otherInfo;
	private String shipperRefNo;
	private String gstType;
	private Long orgId;
	private String createdBy;
	private String cancelRemarks;
	private String branch;
	private String branchCode;
	private String customer;
	private String client;
	private String finYear;
	private String ipNo;
	private String latitude;

	List<ChargerCostInvoiceDTO> chargerCostInvoiceDTO;

	List<TdsCostInvoiceDTO> tdsCostInvoiceDTO;
	
	List<CostInvSummaryDTO> costInvSummaryDTO;
	
	

}
