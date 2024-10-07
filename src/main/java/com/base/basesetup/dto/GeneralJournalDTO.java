package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralJournalDTO {
	private Long id;
	private String voucherSubType;
	private LocalDateTime docDate;
	private String docId;
	
	private String currency;
	private String exRate;
	private String refNo;
	private LocalDateTime refDate;

	private String remarks;
	private Long orgId;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String createdBy;
	private BigDecimal totalDebitAmount;
	private BigDecimal totalCreditAmount;

	List<ParticularsJournalDTO> particularsJournalDTO;

}
