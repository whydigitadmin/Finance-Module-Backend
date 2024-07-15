package com.base.basesetup.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargerTaxInvoiceDTO {
	private Long id;
	private String type;
	private String chargeCode;
	private String gChargeCode;
	private String chargeName;
	private String taxable;
	private int qty;
	private String rate;
	private String currency;
	private String exRate;
	private String fcAmount;
	private String lcAmount;
	private String billAmount;
	private String sac;
	private String GSTPercent;
	private String GST;

}
