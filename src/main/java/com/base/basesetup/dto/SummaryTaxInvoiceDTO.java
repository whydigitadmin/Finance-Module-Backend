package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryTaxInvoiceDTO {
	private Long id;
	private String lcChargeAmount;
	private String lcTaxAmount;
	private String lcInvAmount;
	private String lcRoundOffAmount;
	private String billlcChargeAmount;
	private String billTaxAmount;
	private String billInvAmount;
	private String lcTaxableAmount;
	private String amountInwords;
	private String billingRemarks;
}
