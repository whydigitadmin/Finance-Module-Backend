package com.base.basesetup.dto;


import java.math.BigDecimal;

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
	private BigDecimal  rate;
	private String currency;
	private BigDecimal exRate;
	private BigDecimal fcAmount;
	private BigDecimal lcAmount;
	private BigDecimal billAmount;
	private String sac;
	private BigDecimal GSTPercent;
	private BigDecimal GST;

}
