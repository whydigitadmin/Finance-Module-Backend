package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartCostCenterDTO {
	private Long id;
	private String costCenterCode;
	private String costCenterName;
	private String credit;
	private String debit;
	private Long orgId;
	private boolean active;
	private String createdBy;
	private String updatedBy;

}
