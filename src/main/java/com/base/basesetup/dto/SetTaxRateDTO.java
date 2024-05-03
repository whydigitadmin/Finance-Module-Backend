package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetTaxRateDTO {

	private Long setTaxRateId;

	private Long orgId;

	private String chapter;

	private String subChapter;

	private String hsnCode;

	private String branch;

	private float newRate;

	private char excepmted;
}
