package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TdsMasterDTO {
	private Long tdsMasterId;
	private Long orgId;
	private String section;
	private String sectionName;
}
