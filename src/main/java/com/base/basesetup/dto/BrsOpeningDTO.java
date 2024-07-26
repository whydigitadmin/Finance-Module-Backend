package com.base.basesetup.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrsOpeningDTO {

	private Long id;
	private String billNo;
	private LocalDateTime billDate;
	private String chqNo;
	private LocalDateTime chqDate;
	private String bank;
	private String currency;
	private String exRate;
	private String receiptAmount;
	private String paymentAmount;
	private boolean reconcile;
	private Long orgId;
	private boolean active;
	private String createdBy;
}
