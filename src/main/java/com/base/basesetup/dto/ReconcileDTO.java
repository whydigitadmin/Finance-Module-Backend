package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReconcileDTO {
	private Long id;
	private String branch;
	private String docId;
	private LocalDateTime docDate;
	private LocalDateTime bankStmtDate;
	private String bankAccount;
	private LocalDateTime lastReconciledOn;
	private LocalDateTime clearedDate;
	private BigDecimal ledgerBalance;
	private BigDecimal beginingBalance;
	private BigDecimal endingBalance;
	private Long orgId;
	private boolean active;
	private String createdBy;
	private BigDecimal totalWithdrawal;
	private BigDecimal totalDeposit;
	private BigDecimal summaryEndingBalance;
	private BigDecimal clearedBalance;
	private BigDecimal difference;
	private String narration;
	
	List<WithdrawalsReconcileDTO> withdrawalsReconcileDTO;
	List<DepositsReconcileDTO> depositsReconcileDTO;
}
