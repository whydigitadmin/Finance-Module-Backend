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
@Table(name = "gsttaxinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GstTaxInvoiceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gsttaxinvoicegen")
	@SequenceGenerator(name = "gsttaxinvoicegen", sequenceName = "gsttaxinvoiceVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "gsttaxinvoiceid")
	private Long id;
	@Column(name = "gstchargeacc")
	private String gstChargeAcc;
	@Column(name = "gstsubledgecode")
	private String gstSubledgerCode;
	@Column(name = "gstbdbillamount")
	private BigDecimal gstBdBillAmount;
	@Column(name = "gstcrbillamount")
	private BigDecimal gstCrBillAmount;
	@Column(name = "gstdblcamount")
	private BigDecimal gstDbLcAmount;
	@Column(name = "gstcrlcamount")
	private BigDecimal gstCrLcAmount;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "taxinvoiceid")
	TaxInvoiceVO taxInvoiceVO;
	
}
