package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticularsReconcileDTO {
	private Long id;
	private String voucherNo;
	private LocalDateTime voucherDate;
	private String chequeNo;
	private LocalDateTime chequeDate;
	private BigDecimal deposit;
	private BigDecimal withdrawal;
	private String bankRef;
}
