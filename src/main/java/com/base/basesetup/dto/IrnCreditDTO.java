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
public class IrnCreditDTO {
	private Long id;
	// Common fields
	private String branch;
	private String branchCode;
	private String createdBy;
	private String updatedBy;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String finYear;
	private String ipNo;
	private Long orgId;
	// IRN fields
	private String vohNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate vohDate;
	private String partyName;
	private String partyCode;
	private String supRefNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate supRefDate;
	private String partyType;
	private String product;
	private int creditDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dueDate;
	private String state;
	private String city;
	private String officeType;
	private String currency;
	private String status;
	private String originBill;
	private String remarks;
	private String address;
	private String shipRefNo;
	private int pincode;
	private String gstin;
	private String gstType;
	private String billingMonth;
	private String otherInfo;
	private String salesType;
	private String creditRemarks;
	private String charges;
	// SummaryFields
	private BigDecimal totChargesBillCurrAmt;
	private BigDecimal totChargesLCAmt;
	private BigDecimal totGrossBillAmt;
	private BigDecimal totGrossLCAmt;
	private BigDecimal netBillCurrAmt;
	private BigDecimal netLCAmt;
	private String amtInWords;
	private BigDecimal exRate;
	private BigDecimal totTaxAmt;

	List<IrnCreditChargesDTO> irnCreditChargeDTO;

	List<IrnCreditGstDTO> irnCreditGstDTO;

}
