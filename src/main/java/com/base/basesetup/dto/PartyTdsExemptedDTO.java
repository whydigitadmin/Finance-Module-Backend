package com.base.basesetup.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyTdsExemptedDTO {
	private Long id;
	private String tdsExempCerti;
    private BigDecimal value;
    private String finYear;
}
