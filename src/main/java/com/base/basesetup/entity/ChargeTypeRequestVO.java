package com.base.basesetup.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chargetyperequest")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargeTypeRequestVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chargetyperequestgen")
	@SequenceGenerator(name = "chargetyperequestgen", sequenceName = "chargetyperequestseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "chargetyperequestid")
	private Long id;
	@Column(name = "chargetype")
	private String chargeType;
	@Column(name = "chargecode")
	private String chargeCode;
	@Column(name = "chargedescription")
	private String chargeDescription;
	@Column(name = "product")
	private String product;
	@Column(name = "localchargedescripition")
	private String localChargeDescripition;
	@Column(name = "serviceaccountcode")
	private String serviceAccountCode;
	@Column(name = "sacdescripition")
	private String sacDescripition;
	@Column(name = "salesaccount")
	private String salesAccount;
	@Column(name = "purchaseaccount")
	private String purchaseAccount;
	@Column(name = "taxable")
	private String taxable;
	@Column(name = "taxtype")
	private String taxType;
	@Column(name = "ccfeeapplicable")
	private String ccFeeApplicable;
	@Column(name = "taxablepercentage")
	private float taxablePercentage;
	@Column(name = "ccjob")
	private String ccJob;
	@Column(name = "govtsac")
	private String govtSac;
	@Column(name = "excempted")
	private String excempted;
	@Column(name = "gsttax")
	private String gstTax;
	@Column(name = "gstcontrol")
	private String gstControl;
	@Column(name = "service")
	private String service;
	@Column(name = "type")
	private String type;
	@Column(name = "salesledger")
	private String salesLedger;
	@Column(name = "purchaseledger")
	private String purchaseLedger;
	@Column(name = "effromdate")
	private LocalDateTime effromDate;
	@Column(name = "eftodate")
	private LocalDateTime eftoDate;

	@Column(name = "active")
	private boolean active;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancel")
	private Long cancel;
	@Column(name = "cancelremarks")
	private Long cancelRemarks;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
