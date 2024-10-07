package com.base.basesetup.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "particularsjournal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticularsJournalVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "particularsjournalgen")
	@SequenceGenerator(name = "particularsjournalgen", sequenceName = "particularsjournalseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "particularsjournalid")
	private Long id;
	@Column(name = "accountsname")
	private String accountsName;
	@Column(name = "subledgername")
	private String subledgerName;
	@Column(name = "subledgercode")
	private BigDecimal subLedgerCode;
	@Column(name = "debitamount")
	private BigDecimal debitAmount;
	@Column(name = "creditamount")
	private BigDecimal creditAmount;
	@Column(name = "narration")
	private String narration;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "generaljournalid")
	GeneralJournalVO generalJournalVO;
}
