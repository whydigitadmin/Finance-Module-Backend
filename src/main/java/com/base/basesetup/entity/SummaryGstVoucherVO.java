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
@Table(name = "summarygstvoucher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryGstVoucherVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summarygstvouchergen")
	@SequenceGenerator(name = "summarygstvouchergen", sequenceName = "summarygstvoucherseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "summarygstvoucherid")
	private Long id;
	@Column(name = "totaldebitamount")
	private BigDecimal totalDebitAmount;
	@Column(name = "totalcreditamount")
	private BigDecimal totalCreditAmount;
	@Column(name = "sttaxamount")
	private BigDecimal stTaxAmount;
	@Column(name = "basamount")
	private BigDecimal basAmount;
	@Column(name = "bssamount")
	private BigDecimal bssAmount;
	@Column(name = "chaamount")
	private BigDecimal chaAmount;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "gstsalesvoucherid")
	GstSalesVoucherVO gstSalesVoucherVO;
}
