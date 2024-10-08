package com.base.basesetup.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReconcileCorpBankDTO {
	private Long id;
	private String docId;
	private LocalDateTime docDate;
	private String bankAccount;
	private LocalDateTime bankStmtDate;
	private String remarks;
	
	private Long orgId;
	private boolean active;
	private String createdBy;

	List<ParticularsReconcileCorpBankDTO> particularsReconcileCorpBankDTO;
}
