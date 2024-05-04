package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxMasterDTO {
	private Long taxMasterId;
	private Long orgId;
	private String section;
	private String sectionName;

}
