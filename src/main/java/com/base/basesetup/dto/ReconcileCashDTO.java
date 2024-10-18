package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReconcileCashDTO {
	private Long id;
	private String docId;
	private LocalDate docDate;
	private String cashAccount;
	private BigDecimal balanceAsPerBooks;
	private int dn1;
	private int dn2;
	private int dn3;
	private int dn4;
	private int dn5;
	private int dn6;
	private int dn7;
	private int dn8;
	private BigDecimal dn1Amt;
	private BigDecimal dn2Amt;
	private BigDecimal dn3Amt;
	private BigDecimal dn4Amt;
	private BigDecimal dn5Amt;
	private BigDecimal dn6Amt;
	private BigDecimal dn7Amt;
	private BigDecimal dn8Amt;
	private int totalPhyAmount ;
	private BigDecimal differenceAmount;
	private String remarks;
	
	private Long orgId;
	private String branch;
	private String branchCode;
	private String createdBy;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String finYear;
	private String ipNo;
	private String latitude;
}
