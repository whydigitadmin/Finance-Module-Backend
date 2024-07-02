package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryCostInvoiceDTO {
	private Long id;
	private double billCurrTotChargeAmt;
	private double billCurrActBillAmt;
	private double billCurrNetAmt;
	private double lcTotChargeAmt;
	private double lcActBillAmt;
	private double lcNetAmt;
	private String roundOff;
	private double lcGstInputAmt;
}
