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
@Table(name = "chargerirncredit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargerIrnCreditVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chargerirncreditgen")
	@SequenceGenerator(name = "chargerirncreditgen", sequenceName = "chargerirncreditVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "chargerirncreditid")
	private Long id;
	@Column(name = "type")
	private String type;
	@Column(name = "chargeCode")
	private String chargeCode;
	@Column(name = "gchargecode")
	private String gChargeCode;
	@Column(name = "chargename")
	private String chargeName;
	@Column(name = "taxable")
	private String taxable;
	@Column(name = "qty")
	private int qty;
	@Column(name = "rate")
	private String rate;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exRate")
	private String exRate;
	@Column(name = "fcAmount")
	private String fcAmount;
	@Column(name = "lcAmount")
	private String lcAmount;
	@Column(name = "billAmount")
	private String billAmount;
	@Column(name = "sac")
	private String sac;
	@Column(name = "gstpercent")
	private String GSTPercent;
	@Column(name = "gst")
	private String GST;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "irncreditid")
	IrnCreditVO irnCreditVO;
}
