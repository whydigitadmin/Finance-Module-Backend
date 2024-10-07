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
@Table(name = "taxinvoicedetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxInvoiceDetailsVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taxinvoicedetailsgen")
	@SequenceGenerator(name = "taxinvoicedetailsgen", sequenceName = "taxinvoicedetailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "taxinvoicedetailsid")
	private Long id;
	@Column(name = "chargetype")
	private String chargeType;
	@Column(name = "chargecode")
	private String chargeCode;
	@Column(name = "govchargecode")
	private String govChargeCode;
	@Column(name = "ledger")
	private String ledger;
	@Column(name = "chargename")
	private String chargeName;
	@Column(name = "taxable")
	private String taxable;
	@Column(name = "qty")
	private int qty;
	@Column(name = "rate")
	private BigDecimal rate;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private BigDecimal exRate;
	@Column(name = "exempted")
	private String exempted;
	@Column(name = "fcamount")
	private BigDecimal fcAmount;
	@Column(name = "lcamount")
	private BigDecimal lcAmount;
	@Column(name = "tlcamount")
	private BigDecimal tlcAmount;
	@Column(name = "billamount")
	private BigDecimal billAmount;
	@Column(name = "sac")
	private String sac;
	@Column(name = "gstpercent")
	private int GSTPercent;
	@Column(name = "gstamount")
	private BigDecimal gstAmount;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "taxinvoiceid")
	TaxInvoiceVO taxInvoiceVO;
}