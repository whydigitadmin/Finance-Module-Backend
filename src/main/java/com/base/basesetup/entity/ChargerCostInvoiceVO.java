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
@Table(name = "chargercostinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargerCostInvoiceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chargercostinvoicegen")
	@SequenceGenerator(name = "chargercostinvoicegen", sequenceName = "chargercostinvoiceseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "chargercostinvoiceid")
	private Long id;
	@Column(name = "chargername")
	private String chargeName;
	@Column(name = "chargecode")
	private String chargeCode;
	@Column(name = "chargeledger")
	private String chargeLedger;
	@Column(name = "gsac")
	private String gsac;
	@Column(name = "conttype")
	private String contType;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private String exRate;
	@Column(name = "rate")
	private String rate;
	@Column(name = "gstpercentage")
	private BigDecimal gstPercentage;
	@Column(name = "fcamount")
	private BigDecimal fcAmount;
	@Column(name = "lcAmount")
	private BigDecimal lcAmount;
	@Column(name = "billamount")
	private BigDecimal billAmount;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "costinvoice_id")
	CostInvoiceVO costInvoiceVO;
	

}
