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
	private Double totChargesBillCurrAmt;
	private Double totChargesLcAmt;
	private Double actBillCurrAmt;
	private Double actBillLcAmt;
	private Double netBillCurrAmt;
	private Double netBillLcAmt;
	private Double roundOff;
	private Double gstInputLcAmt;
	
	
	
}
