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
public class GstTaxInvoiceDTO {
	private Long id;
	private String gstChargeAcc;
	private String gstSubledgerCode;
	private BigDecimal gstBdBillAmount;
	private BigDecimal gstCrBillAmount;
	private BigDecimal gstDbLcAmount;
	private BigDecimal gstCrLcAmount;

}