package com.base.basesetup.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String finYear;
	private String sourceTransid;
	private String refNo;
	private String accName;
	private String currency;
	private String accCurrency;
	private float exRate;
	private float amount;
	private float baseAmt;
	private float nativeAmt;
	private float chargableAmt;
	private boolean gstFlag;
	private String docTypeCode;
	private String subTypeCode;
	private String subLedgerDivision;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime docDate;
	private String suppRefNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime refDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime supRefDate;
	private String subLedgerCode;
	private String creditDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime dueDate;
	private float TDSAmt;
	private String hno;
	private Long orgId;
	private String canelRemarks;
	private String createdBy;
	private String ipNo;
	private String latitude;
	private String subLedgerName;
	private String branchCode;


}
