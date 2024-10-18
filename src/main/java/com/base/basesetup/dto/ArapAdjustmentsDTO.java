package com.base.basesetup.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArapAdjustmentsDTO {

	private Long id;
	private String branch;
	private String finYear;
	private String source;
	private String refNo;
	private String accountName;
	private String currency;
	private String accCurrency;
	private float baseAmnt;
	private float nativeAmt;
	private String offDocId;
	private String voucherType;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime refDate;
	private String subLedgerCode;
	private float exRate;
	private String creditDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime dueDate;
	private Long orgId;
	private String createdBy;
	private String branchCode;
	private String ipNo;
	private String latitude;
	private String transId;
	private float chargeableAmt;
	private float tdsAmt;
	private String subLedgerName;
	private boolean gstFlag;
}
