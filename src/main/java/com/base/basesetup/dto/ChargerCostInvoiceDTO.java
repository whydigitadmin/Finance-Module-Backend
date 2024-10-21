package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargerCostInvoiceDTO {
	private Long id;
	private String houseNo;
	private String jobNo;
	private String subJobNo;
	private String chargeName;
	private String chargeCode;
	private String chargeLedger;
	private String gsac;
	private String contType;
	private Double currency;
	private Double exRate;
	private Long gst;
	private Double fcAmt;
	private Double lcAmt;
	private Double billAmt;
}




