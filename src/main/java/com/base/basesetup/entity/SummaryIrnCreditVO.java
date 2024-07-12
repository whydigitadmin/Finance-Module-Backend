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
@Table(name = "summaryirncredit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryIrnCreditVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summaryirncreditgen")
	@SequenceGenerator(name = "summaryirncreditgen", sequenceName = "summaryirncreditseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "summaryirncreditid")
	private Long id;
	@Column(name = "lcchargeamount")
	private double lcChargeAmount;
	@Column(name = "lctaxamount")
	private double lcTaxAmount;
	@Column(name = "lcinvamount")
	private double lcInvAmount;
	@Column(name = "lcroundoffamount")
	private double lcRoundOffAmount;
	@Column(name = "billchargeamount")
	private double billlcChargeAmount;
	@Column(name = "billtaxamount")
	private double billTaxAmount;
	@Column(name = "billinvamount")
	private double billInvAmount;
	@Column(name = "lctaxableamount")
	private double lcTaxableAmount;
	@Column(name = "amountinwords")
	private String amountInwords;
	@Column(name = "billingremarks")
	private String billingRemarks;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "irncreditid")
	IrnCreditVO irnCreditVO;
}
