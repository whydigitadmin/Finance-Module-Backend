package com.base.basesetup.dto;

import java.math.BigDecimal;

import java.math.BigDecimal;

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
	private BigDecimal billCurrTotChargeAmt;
	private BigDecimal billCurrActBillAmt;
	private BigDecimal billCurrNetAmt;
	private BigDecimal lcTotChargeAmt;
	private BigDecimal lcActBillAmt;
	private BigDecimal lcNetAmt;
	private String roundOff;
	private BigDecimal lcGstInputAmt;
}
