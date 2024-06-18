package com.base.basesetup.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.base.basesetup.entity.GeneralJournalVO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryJournalDTO {

	private Long id;
	private String totalCreditAmount;
	private String totalDebitAmount;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="generaljournalid")
	GeneralJournalVO generalJournalVO;
}
