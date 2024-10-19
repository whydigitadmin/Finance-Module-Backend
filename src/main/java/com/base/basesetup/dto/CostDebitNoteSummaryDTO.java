package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostDebitNoteSummaryDTO {

private Long costDebitNoteSummaryId;
	
	private double totChargesBillCurrAmt;

	private double totChargesLCAmt;

	private double totGrossBillAmt;

	private double totGrossLCAmt;

	private double netBillCurrAmt;

	private double netLCAmt;

	private String amtInWords;
	
}
