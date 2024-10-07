package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalsReconcileDTO {
	private Long id;
	private String voucherNo;
	private LocalDateTime voucherDate;
	private String chqDdNo;
	private LocalDateTime chqDdDate;
	private LocalDateTime clearedDate;
	private BigDecimal paymentAmt;
	private String paymentName;
	private String narration;
}
