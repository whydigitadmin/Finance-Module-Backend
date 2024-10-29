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
	@Column(name = "houseno",length =20)
	private String houseNo;
	@Column(name = "jobno",length =20)
	private String jobNo;
	@Column(name = "subjobno",length =20)
	private String subJobNo;
	@Column(name = "chargername",length =150)
	private String chargeName;
	@Column(name = "chargecode",length =10)
	private String chargeCode;
	@Column(name = "chargeledger",length =150)
	private String chargeLedger;
	@Column(name = "gsac",length =15)
	private String gsac;
	@Column(name = "conttype",length =10)
	private String contType;
	@Column(name = "currency",length =15)
	private String currency;
	@Column(name = "exrate",precision =10,scale =2)
	private BigDecimal exRate;
	@Column(name = "gst",length =45)
	private String gst;
	@Column(name = "fcamt",precision =10,scale =2)
	private BigDecimal fcAmt;
	@Column(name = "lcamt",precision =10,scale =2)
	private BigDecimal lcAmt;
	@Column(name = "billamt",precision =10,scale =2)
	private BigDecimal billAmt;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "costinvoiceid")
	CostInvoiceVO costInvoiceVO;
	

}
