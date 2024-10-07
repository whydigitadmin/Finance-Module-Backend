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
public class ChargerCostInvoiceDTO {
	private Long id;
	private String chargeName;
	private String chargeCode;
	private String chargeLedger;
	private String gsac;
	private String contType;
	private String currency;
	private String exRate;
	private String rate;
	private BigDecimal gstPercentage;
	private BigDecimal fcAmount;
	private BigDecimal lcAmount;
	private BigDecimal billAmount;
}
