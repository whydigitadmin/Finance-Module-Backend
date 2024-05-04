package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TcsMasterDTO {
	private Long tcsMasterId;
	private Long orgId;
	private String section;
	private String sectionName;

}