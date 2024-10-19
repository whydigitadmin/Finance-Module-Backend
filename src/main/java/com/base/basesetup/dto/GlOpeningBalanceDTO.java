package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	private String currency;
	private BigDecimal exRate;
	private String refNo;
	private LocalDate refDate;
	private String suppRefNo;
	private LocalDate suppRefDate;
	private String remarks;
	private Long orgId;
	private String createdBy;
	private String finYear;
	private String branchCode;
	private BigDecimal totalCreditAmount;
	private BigDecimal totalDebitAmount;
	List<ParticularsGlOpeningBalanceDTO>particularsGlOpeningBalanceDTO;
}
