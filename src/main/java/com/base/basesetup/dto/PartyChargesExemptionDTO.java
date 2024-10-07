package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyChargesExemptionDTO {
	private Long id;
	private String tdsSection;
	private String charge;
}
