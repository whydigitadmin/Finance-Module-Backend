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
@Table(name = "summarytaxinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryTaxInvoiceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summarytaxinvoicegen")
	@SequenceGenerator(name = "summarytaxinvoicegen", sequenceName = "summarytaxinvoiceVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "summarytaxinvoiceid")
	private Long id;
	@Column(name = "lcchargeamount")
	private String lcChargeAmount;
	@Column(name = "lctaxamount")
	private String lcTaxAmount;
	@Column(name = "lcinvamount")
	private String lcInvAmount;
	@Column(name = "lcroundoffamount")
	private String lcRoundOffAmount;
	@Column(name = "billchargeamount")
	private String billlcChargeAmount;
	@Column(name = "billtaxamount")
	private String billTaxAmount;
	@Column(name = "billinvamount")
	private String billInvAmount;
	@Column(name = "lctaxableamount")
	private String lcTaxableAmount;
	@Column(name = "amountinwords")
	private String amountInwords;
	@Column(name = "billingremarks")
	private String billingRemarks;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "taxinvoiceid")
	TaxInvoiceVO taxInvoiceVO;
}
