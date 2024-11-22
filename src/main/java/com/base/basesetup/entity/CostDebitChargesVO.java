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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "costdebitcharges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostDebitChargesVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costdebitchargesgen")
	@SequenceGenerator(name = "costdebitchargesgen", sequenceName = "costdebitchargesseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "costdebitchargesid")
	private Long id;

//	@Column(name = "jobtype", length = 20)
//	private String jobType;
//
//	@Column(name = "jobno", length = 20)
//	private String jobNo;
//
//	@Column(name = "subjobno", length = 20)
//	private String subJobNo;
//
//	@Column(name = "houseno", length = 25)
//	private String houseNo;
//
//	@Column(name = "chargecode", length = 15)
//	private String chargeCode;
//
//	@Column(name = "gchargecode", length = 15)
//	private String gChargeCode;
//
//	@Column(name = "gsac", length = 15)
//	private String gSAC;
//
//	@Column(name = "chargename", length = 150)
//	private String chargeName;
//
//	@Column(name = "applyon", length = 15)
//	private String applyOn;
//
//	@Column(name = "tax")
//	private boolean tax;
//
//	@Column(name = "currency", length = 10)
//	private String currency;
//
//	@Column(name = "exrate", precision = 10, scale = 2)
//	private BigDecimal exRate;
//
//	@Column(name = "rate", precision = 10, scale = 2)
//	private BigDecimal rate;
//
//	@Column(name = "exampted")
//	private boolean exampted;
//
//	@Column(name = "fcamt", precision = 10, scale = 2)
//	private BigDecimal fcAmt;
//
//	@Column(name = "lcamt", precision = 10, scale = 2)
//	private BigDecimal lcAmt;
//
//	@Column(name = "taxpercentage", precision = 3)
//	private int taxPercentage;
//
//	@Column(name = "tlcamt", precision = 10, scale = 2)
//	private BigDecimal tlcAmt;
//
//	@Column(name = "billamt", precision = 10, scale = 2)
//	private BigDecimal billAmt;
//
//	@Column(name = "gstpercentage", precision = 3)
//	private int gstPercentage;
//
//	@Column(name = "gst", precision = 10, scale = 2)
//	private BigDecimal gst;
//	
//	@Column(name = "qty")
//	private int qty;
//	
//	@Column(name = "ledger",length = 150)
//	private String ledger;

	@Column(name = "jobno", length = 20)
	private String jobNo;
	@Column(name = "chargername", length = 150)
	private String chargeName;
	@Column(name = "chargecode", length = 25)
	private String chargeCode;
	@Column(name = "chargeledger", length = 150)
	private String chargeLedger;
	@Column(name = "sac", length = 15)
	private String sac;
	@Column(name = "conttype", length = 10)
	private String contType;
	@Column(name = "currency", length = 15)
	private String currency;
	@Column(name = "exrate", precision = 10, scale = 2)
	private BigDecimal exRate;
	@Column(name = "gst", length = 45)
	private String gst;
	@Column(name = "fcamt", precision = 10, scale = 2)
	private BigDecimal fcAmt;
	@Column(name = "lcamt", precision = 10, scale = 2)
	private BigDecimal lcAmt;
	@Column(name = "billamt", precision = 10, scale = 2)
	private BigDecimal billAmt;
	@Column(name = "rate", precision = 10, scale = 2)
	private BigDecimal rate;
	@Column(name = "qty")
	private int qty;
	@Column(name = "gstpercent")
	private Float GSTPercent;
	@Column(name = "tlcamount", precision = 10, scale = 2)
	private BigDecimal tlcAmount;
	@Column(name = "gstamount", precision = 10, scale = 2)
	private BigDecimal gstAmount;
	@Column(name = "ledger", length = 150)
	private String ledger;
	@Column(name = "govchargecode", length = 10)
	private String govChargeCode;
	@Column(name = "exempted", precision = 10, scale = 2)
	private String exempted;
	@Column(name = "taxable", length = 10)
	private String taxable;

	@ManyToOne
	@JoinColumn(name = "costdebitnoteid")
	@JsonBackReference
	private CostDebitNoteVO costDebitNoteVO;

}
