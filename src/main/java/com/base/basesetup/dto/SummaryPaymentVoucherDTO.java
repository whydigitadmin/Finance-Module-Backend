package com.base.basesetup.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryPaymentVoucherDTO {
	private Long id;
	private BigDecimal totalDebitAmount;
	private BigDecimal totalCreditAmount;

}
