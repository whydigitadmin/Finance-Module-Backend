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
@Table(name = "particularspaymentvoucher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticularsPaymentVoucherVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "particularspaymentvouchergen")
	@SequenceGenerator(name = "particularspaymentvouchergen", sequenceName = "particularspaymentvoucherseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "particularspaymentvoucherid")
	private Long id;
	@Column(name = "accountname")
	private String accountName;
	@Column(name = "subledgername")
	private String subLedgerName;
	@Column(name = "subledgercode")
	private String subLedgerCode;
	@Column(name = "debit")
	private BigDecimal debit;
	@Column(name = "credit")
	private BigDecimal credit;
	@Column(name = "narration")
	private String narration;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "paymentvoucherid")
	PaymentVoucherVO paymentVoucherVO;
}
