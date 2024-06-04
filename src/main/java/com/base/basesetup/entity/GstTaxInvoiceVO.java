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
	private double gstBdBillAmount;
	@Column(name = "gstcrbillamount")
	private double gstCrBillAmount;
	@Column(name = "gstdblcamount")
	private double gstDbLcAmount;
	@Column(name = "gstcrlcamount")
	private double gstCrLcAmount;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "taxinvoice")
	TaxInvoiceVO taxInvoiceVO;

}
