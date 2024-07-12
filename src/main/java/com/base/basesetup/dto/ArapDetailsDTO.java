package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArapDetailsDTO {
	private Long id;
	private String branch;
	private String finyr;
	private String sourceTransid;
	private String docId;
	private String refNo;
	private String accountName;
	private String currency;
	private String accountCurrency;
	private String account;
	private BigDecimal exRate;
	private String amount;
	private String baseAmount;
	private String nativeAmount;
	private String mno;
	private String chargableAmount;
	private String gstFlag;
	private String docTypeCode;
	private String subTypeCode;
	private String subLedgerDivision;
	private LocalDateTime docDate;
	private String suppRefNo;
	private LocalDateTime refDate;
	private LocalDateTime suppRefDate;
	private String subLedgerCode;
	private String creditDays;
	private LocalDateTime dueDate;
	private String tdsAmount;
	private String hno;
	private String orgId;
	private boolean active;
	private String createdBy;
	private String updatedBy;
}
