package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CostDebitNoteTaxPrtculDTO {
	
	private Long Id;
	
	private String tds;

	private double tdsPercentage;

	private String section;

	private double totTDSAmt;

}
