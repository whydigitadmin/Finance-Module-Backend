package com.base.basesetup.dto;

import java.time.LocalDate;
import java.util.List;

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
	private String purVchNo;
	private LocalDate purVchDt;
	private String costInvoiceNo;
	private LocalDate date;
	private String supplierBillNo;
	private String suppliertType;
	private String supplierCode;
	private String creditDays;
	private LocalDate dueDate;
	private String supplierName;
	private String currency;
	private String exRate;
	private String supplierGstIn;
	private String remarks;
	private String address;
	private String otherInfo;
	private String shipperRefNo;
	private String gstType;
	private String payment;
	private String accrualId;
	private String utrReference;
	private String costType;
	private String jobStatus;
	private Long orgId;
	private boolean active;
	private String updatedBy;
	private String createdBy;

	List<ChargerCostInvoiceDTO> chargerCostInvoiceDTO;

	
	List<TdsCostInvoiceDTO> tdsCostInvoiceDTO;

	List<SummaryCostInvoiceDTO> summaryCostInvoiceDTO;

}
