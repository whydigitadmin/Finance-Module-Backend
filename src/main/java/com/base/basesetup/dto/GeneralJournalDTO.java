package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String currency;
	private BigDecimal exRate;
	private String refNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate refDate;

	private String remarks;
	private Long orgId;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String createdBy;
	private String branch;
	private String branchCode;
	private String finYear;
	private BigDecimal totalDebitAmount;
	private BigDecimal totalCreditAmount;

	List<ParticularsJournalDTO> particularsJournalDTO;

}
