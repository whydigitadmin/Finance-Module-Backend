package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CostDebitNoteGstDTO {

private Long costDebitNoteGstId;
	
	private String chargeAcc;
	private String subLodgerCode;
	private double dBillAmt;
	private double crBillAmt;
	private double dBLCAmt;
	private double crLCAmt;
	private String remarks;
}
