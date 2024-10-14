package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRegisterDTO {

	private Long id;
	private Long voucherNo;
	private LocalDate voucherDate;
	private String receivedFrom;
	private String currency;
	private BigDecimal exRate;
	private Long amount;
	private Long lcAmount;
	
	private Long orgId;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String createdBy;

}
