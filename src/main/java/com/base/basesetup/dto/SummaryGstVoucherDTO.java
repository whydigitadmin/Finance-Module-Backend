package com.base.basesetup.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryGstVoucherDTO {
	private Long id;
	private BigDecimal totalDebitAmount;
	private BigDecimal totalCreditAmount;
	private BigDecimal stTaxAmount;
	private BigDecimal basAmount;
	private BigDecimal bssAmount;
	private BigDecimal chaAmount;
}
