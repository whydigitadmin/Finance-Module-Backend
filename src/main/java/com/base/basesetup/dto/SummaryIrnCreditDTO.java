package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryIrnCreditDTO {
	private Long id;
	private double lcChargeAmount;
	private double lcTaxAmount;
	private double lcInvAmount;
	private double lcRoundOffAmount;
	private double billlcChargeAmount;
	private double billTaxAmount;
	private double billInvAmount;
	private double lcTaxableAmount;
	private String amountInwords;
	private String billingRemarks;
}
