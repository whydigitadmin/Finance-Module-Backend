package com.base.basesetup.dto;

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
	private String currecy;
	private String subLedgerCode;
	private String debitAmount;
	private String creditAmount;
	private String narration;

}
