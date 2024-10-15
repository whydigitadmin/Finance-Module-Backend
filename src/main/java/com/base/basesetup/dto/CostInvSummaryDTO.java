package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostInvSummaryDTO {

	private Long id;
	private float totChargesBillCurrAmt;
	private float totChargesLcAmt;
	private float actBillCurrAmt;
	private float actBillLcAmt;
	private float netBillCurrAmt;
	private float netBillLcAmt;
	private float roundOff;
	private float gstInputLcAmt;
	
	
	
}
