package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adjustmentoffset")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdjustmentOffSetVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adjustmentoffsetgen")
	@SequenceGenerator(name = "adjustmentoffsetgen", sequenceName = "adjustmentoffsetseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "adjustmentoffsetid")
	private Long id;
	@Column(name = "docid")
	private String docId;
	@Column(name = "docdate")
	private LocalDate docDate;
	@Column(name = "subledgertype")
	private String subLedgerType;
	@Column(name = "subledgername")
	private String subLedgerName;
	@Column(name = "subledgercode")
	private String subLedgerCode;
	@Column(name = "receiptpaymentdocid")
	private String receiptPaymentDocId;
	@Column(name = "receiptpaymentdocdate")
	private LocalDate receiptPaymentDocDate;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private BigDecimal exRate;
	@Column(name = "amount")
	private String amount;
	@Column(name = "supplierRefNo")
	private String supplierRefNo;
}
