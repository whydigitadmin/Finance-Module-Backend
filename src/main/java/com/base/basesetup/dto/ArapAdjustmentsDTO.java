package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArapAdjustmentsDTO {

	private Long id;
	private String branch;
	private String finyr;
	private String sourceTransId;
	private String docId;
	private String refNo;
	private String accountName;
	private String currency;
	private String accountCurrency;
	private BigDecimal aexRate;
	private String amount;
	private String baseAmount;
	private String nativeAmount;
	private String offDocId;
	private String voucherType;
	private String subTypeCode;
	private LocalDateTime docDate;
	private LocalDateTime refDate;
	private String subLedgerCode;
	private BigDecimal exRate;
	private String creditDays;
	private LocalDateTime dueDate;
	private String orgId;
	private boolean active;
	private String createdBy;
	private String updatedBy;
}
