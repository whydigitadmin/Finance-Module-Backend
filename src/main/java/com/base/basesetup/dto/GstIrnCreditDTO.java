package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GstIrnCreditDTO {
	private Long id;
	private String gstChargeAcc;
	private String gstSubledgerCode;
	private double gstBdBillAmount;
	private double gstCrBillAmount;
	private double gstDbLcAmount;
	private double gstCrLcAmount;

}
