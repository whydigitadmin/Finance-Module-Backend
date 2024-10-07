package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlOpeningBalanceDTO {
	private Long id;
	private String branch;
	private LocalDateTime docDate;
	private String docId;
	private String currency;
	private String exRate;
	private String refNo;
	private LocalDateTime refDate;
	private String suppRefNo;
	private LocalDateTime suppRefDate;
	private String remarks;
	private Long orgId;
	private boolean active;
	private String createdBy;
	private BigDecimal totalCreditAmount;
	private BigDecimal totalDebitAmount;
	List<ParticularsGlOpeningBalanceDTO>particularsGlOpeningBalanceDTO;
}
