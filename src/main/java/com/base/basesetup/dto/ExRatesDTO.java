package com.base.basesetup.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExRatesDTO {
	private Long id;
	private LocalDate docDate;
	private LocalDate docMonth;
	private String currency;
	private String sellRate;
	private String buyRate;
	private String avgRate;
	private Long orgId;
	private boolean active;
	private String createdBy;
	private String updatedBy;
}
