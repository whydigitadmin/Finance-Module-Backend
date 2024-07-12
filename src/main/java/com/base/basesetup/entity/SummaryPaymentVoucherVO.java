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
@Table(name = "summarypaymentvoucher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryPaymentVoucherVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summarypaymentvouchergen")
	@SequenceGenerator(name = "summarypaymentvouchergen", sequenceName = "summarypaymentvoucherseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "summarypaymentvoucherid")
	private Long id;
	@Column(name = "totaldebitamount")
	private BigDecimal totalDebitAmount;
	@Column(name = "totalcreditamount")
	private BigDecimal totalCreditAmount;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "paymentvoucherid")
	PaymentVoucherVO paymentVoucherVO;
}
