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
	@Column(name = "chargetype",length = 30)
	private String chargeType;
	@Column(name = "chargecode",length = 30)
	private String chargeCode;
	@Column(name = "govchargecode",length = 10)
	private String govChargeCode;
	@Column(name = "ledger",length = 150)
	private String ledger;
	@Column(name = "chargename",length = 150)
	private String chargeName;
	@Column(name = "taxable",length = 10)
	private String taxable;
	@Column(name = "qty")
	private int qty;
	@Column(name = "rate", precision = 10, scale = 2)
	private BigDecimal rate;
	@Column(name = "currency",length = 10)
	private String currency;
	@Column(name = "exrate", precision = 10, scale = 2)
	private BigDecimal exRate;
	@Column(name = "exempted", precision = 10, scale = 2)
	private String exempted;
	@Column(name = "fcamount", precision = 10, scale = 2)
	private BigDecimal fcAmount;
	@Column(name = "lcamount", precision = 10, scale = 2)
	private BigDecimal lcAmount;
	@Column(name = "tlcamount", precision = 10, scale = 2)
	private BigDecimal tlcAmount;
	@Column(name = "billamount", precision = 10, scale = 2)
	private BigDecimal billAmount;
	@Column(name = "sac",length = 30)
	private String sac;
	@Column(name = "gstpercent")
	private int GSTPercent;
	@Column(name = "gstamount", precision = 10, scale = 2)
	private BigDecimal gstAmount;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "taxinvoiceid")
	TaxInvoiceVO taxInvoiceVO;
}