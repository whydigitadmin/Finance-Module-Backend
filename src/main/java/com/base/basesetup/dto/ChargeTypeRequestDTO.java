package com.base.basesetup.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeTypeRequestDTO {
	private Long id;
	private String chargeType;
	private String chargeCode;
	private String chargeDescription;
	private String product;
	private String localChargeDescripition;
	private String serviceAccountCode;
	private String sacDescripition;
	private String salesAccount;
	private String purchaseAccount;
	private String taxable;
	private String taxType;
	private String ccFeeApplicable;
	private float taxablePercentage;
	private String ccJob;
	private String govtSac;
	private String excempted;
	private String gstTax;
	private String gstControl;
	private String service;
	private String type;
	private String salesLedger;
	private String purchaseLedger;
	private LocalDateTime effromDate;
	private LocalDateTime eftoDate;

	private boolean active;
	private Long orgId;
	private Long createdBy;
}
