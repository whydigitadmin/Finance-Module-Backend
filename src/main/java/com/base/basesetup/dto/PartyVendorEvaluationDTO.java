package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyVendorEvaluationDTO {
	private Long id;
	private String whoBroughtVendor;
	private String whatBasisVendorSelected;
	private String justification;
	private String slaPoints;
	private String commonAgreedTerms;
}