package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyTdsExemptedDTO {
	private Long id;
	private String tdsExemptedCertificate;
	private String value;
	private String finYear;
}
