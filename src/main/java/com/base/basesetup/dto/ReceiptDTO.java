package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptDTO {
	private Long id;

	// Receipt fields
	private String type;
	private String customerName;
	private String customerCode;
	private String bankCashAcc;
	private BigDecimal receiptAmt;
	private String bankChargeAcc;
	private BigDecimal bankCharges;
	private String inCurrencyBnkChargs;
	private BigDecimal tdsAmt;
	private BigDecimal taxAmt;
	private String inCurrencyTdsAmt;
	private String chequeBank;
	private String receiptType;
	private String chequeUtiNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate chequeUtiDt;
	private String receivedFrom;
	private String netAmount;
	private String remarks;

	// Common Fields
	private String branch;
	private String branchCode;
	private String customer;
	private String client;
	private String createdBy;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String finYear;
	private String ipNo;
	private String latitude;
	private String receiptType1;
	private String currency;
	private String currencyAmount;
	private Long orgId;

	List<ReceiptInvDetailsDTO> receiptInvDetailaDTO;
}
