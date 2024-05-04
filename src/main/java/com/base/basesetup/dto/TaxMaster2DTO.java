package com.base.basesetup.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxMaster2DTO {

	private long SerialNo;
	private LocalDate fromDate;
	private LocalDate toDate;
	private float tcsPercentage;
}
