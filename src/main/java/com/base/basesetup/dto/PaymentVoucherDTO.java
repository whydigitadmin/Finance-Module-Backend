package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVoucherDTO {
	private Long id;
	private String vehicleSubType;
	private String referenceNo;
	private String currency;
	private String docId;
	private LocalDate referenceDate;
	private BigDecimal exRate;
	private LocalDate docDate;
	private String chequeNo;
	private LocalDate chequeDate;
	private String chequeBank;
	private String remarks;
	private Long orgId;
	private String branch;
	private String branchCode;
	private String createdBy;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String ipNo;
	private String latitude;
	private BigDecimal totalDebitAmount;
	private BigDecimal totalCreditAmount;

	List<ParticularsPaymentVoucherDTO> particularsPaymentVoucherDTO;
}
