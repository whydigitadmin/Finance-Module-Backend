package com.base.basesetup.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundTransferDTO {
	private Long id;
	private String branch;
	private String docId;
	private String paymentType;
	private LocalDateTime docDate;
	private String referenceNo;
	private LocalDateTime referenceDate;
	private String fromAccount;
	private String balance;
	private String currency;
	private String exRate;
	private String toBranch;
	private String toBank;
	private String chequeBook;
	private String chequeNo;
	private LocalDateTime chequeDate;
	private String paymentAmount;
	private String conversionRate;
	private String receiptAmount;
	private String gainLoss;
	private String remarks;
	private boolean active;
	private Long orgId;
	private String createdBy;
	private String updatedBy;
}
