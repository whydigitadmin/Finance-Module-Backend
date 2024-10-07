package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartySalesPersonTaggingDTO {
	private Long id;
	private String salesPerson;
	private String empCode;
	private String salesBranch;
	private String effectiveFrom;
	private String effectiveTill;
}


