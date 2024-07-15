package com.base.basesetup.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSummaryDTO {

	private Long id;
	private BigDecimal foxenGainOrLoss;
	private BigDecimal roundOffAmount;
	private BigDecimal totalSettled;
	private BigDecimal otherAccNetAmt;
	private BigDecimal onAccount;
	private String narration;

}
