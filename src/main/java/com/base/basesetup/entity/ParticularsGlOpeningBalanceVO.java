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
@Table(name = "particularsglopeningbalance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticularsGlOpeningBalanceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "particularsglopeningbalancegen")
	@SequenceGenerator(name = "particularsglopeningbalancegen", sequenceName = "particularsglopeningbalanceseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "particularsglopeningbalanceid")
	private Long id;
	@Column(name="accountname")
	private String accountName;
	@Column(name = "subledgercode")
	private BigDecimal subLedgerCode;
	@Column(name = "debitamount")
	private BigDecimal debitAmount;
	@Column(name = "creditamount")
	private BigDecimal creditAmount;
	@Column(name = "debitbase")
	private BigDecimal debitBase;
	@Column(name = "creditbase")
	private BigDecimal creditBase;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "glopeningbalanceid")
	GlOpeningBalanceVO glOpeningBalanceVO;
}
