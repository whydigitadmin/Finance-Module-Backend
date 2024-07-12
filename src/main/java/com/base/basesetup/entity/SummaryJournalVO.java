package com.base.basesetup.entity;

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

@Entity
@Table(name = "summaryjournal")
public class SummaryJournalVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "summaryjournalgen")
	@SequenceGenerator(name = "summaryjournalgen", sequenceName = "summaryjournalseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "summaryjournalid")
	private long id;
	@Column(name = "totaldebitamount")
	private String totalDebitAmount;
	@Column(name = "totalcreditamount")
	private String totalCreditAmount;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "generaljournalid")
	GeneralJournalVO generalJournalVO; 
}
