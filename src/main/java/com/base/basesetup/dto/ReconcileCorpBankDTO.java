package com.base.basesetup.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReconcileCorpBankDTO {
	private Long id;
	private String bankAccount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate bankStmtDate;
	private String remarks;

	private Long orgId;
	private String branch;
	private String branchCode;
	private boolean active;
	private String createdBy;
	private String finYear;
	private String ipNo;
	private String latitude;

	List<ParticularsReconcileCorpBankDTO> particularsReconcileCorpBankDTO;
}
