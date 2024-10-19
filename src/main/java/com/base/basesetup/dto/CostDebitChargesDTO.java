package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostDebitChargesDTO {

	private Long id;

	private String jobType;

	private String jobNo;

	private String subJobNo;

	private String houseNo;

	private String chargeCode;

	private String gChargeCode;

	private String gSAC;

	private String chargeName;

	private String applyOn;

	private String currency;

	private double exRate;
	
	private double rate;

	private double fcAmt;

	private double lcAmt;

	private int taxPercentage;

	private double tlcAmt;

	private double billAmt;

	private int gstPercentage;

	private double gst;
	
}
