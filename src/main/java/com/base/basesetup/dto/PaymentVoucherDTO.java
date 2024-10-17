package com.base.basesetup.dto;

import java.time.LocalDate;
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
	private LocalDate referenceDate;
	private String remarks;
	private String finyear;
	private Long orgId;
	private String cancelRemarks;
	private String createdBy;
	private String chequeNo;
	private String chequeDate;
	private String chequeBank;
	private String branch;
	private String branchCode;
	private String screenCode;
	private String screenName;
	private String currency;

	List<ParticularsPaymentVoucherDTO> particularsPaymentVoucherDTO;
	
	List<PaymentSummaryDTO> paymentSummaryDTO;
	
}
