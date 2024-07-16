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
public class ParticularsJournalDTO {
	private Long id;
	private BigDecimal currency;
	private BigDecimal subLedgerCode;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private String narration;
	
	

}
