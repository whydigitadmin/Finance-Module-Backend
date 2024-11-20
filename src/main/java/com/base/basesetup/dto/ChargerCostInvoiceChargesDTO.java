package com.base.basesetup.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargerCostInvoiceChargesDTO {
	private Long id;
	private String jobNo;
	private String chargeName;
	private String chargeCode;
	private String chargeLedger;
	private String sac;
	private String contType;
	private String currency;
	private BigDecimal exRate;
	private String gst;
	private BigDecimal fcAmt;
	private BigDecimal lcAmt;
	private BigDecimal billAmt;
	private BigDecimal rate;
	private int qty;
	private Float GSTPercent;
	private BigDecimal tlcAmount;
	private BigDecimal gstAmount;
	private String ledger;
	private String govChargeCode;
	private String exempted;
	private String taxable;
}
