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
	private Long credit;
	private Long debit;
	private Long orgId;
	private String branch;
	private String branchCode;
	private String createdBy;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String finYear;

}
