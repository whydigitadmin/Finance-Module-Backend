package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartySpecialTDSDTO {
	
	private Long id;
	private String tdsSection;
	private String rateForm;
	private String rateTo;
	private String tdsPercentage;
	private String surPercentage;
	private String edPercentage;
	private String tdsCertificateNo;
}
