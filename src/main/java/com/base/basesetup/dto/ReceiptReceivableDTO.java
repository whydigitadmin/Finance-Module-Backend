package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptReceivableDTO {
	private Long id;
	private String branch;
	private String receiptType;
	private LocalDate docDate;
	private String docId;
	private String modeOfPayment;
	private String bankCashAc;
	private String currency;
	private BigDecimal exRates;
	private String balance;
	private String receivedFrom;
	private String cheqDdCardBank;
	private String cheqDdCardNo;
	private LocalDate cheqDdDate;
	private boolean reconciled;
	private Long orgId;
	private boolean active;
	private String createdBy;
	private BigDecimal netAmount;
	private String remarks;

	List<ParticularsAccountReceiptDTO> particularsAccountReceiptDTO;
}
