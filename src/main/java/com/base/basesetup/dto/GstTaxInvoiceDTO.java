package com.base.basesetup.dto;

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
	private String gstBdBillAmount;
	private String gstCrBillAmount;
	private String gstDbLcAmount;
	private String gstCrLcAmount;

}
