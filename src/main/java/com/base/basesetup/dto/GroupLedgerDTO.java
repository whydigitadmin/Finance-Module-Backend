package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupLedgerDTO {

	private Long id;
	private String groupName;
	private String gstTaxFlag;
	private String coaList;
	private String accountGroupName;
	private String type;
	private boolean interBranchAc;
	private boolean controllAc;
	private String category;
	private String currency;

//	Common Fields
	private Long orgId;
	private String branch;
	private String branchCode;
	private String createdBy;
	private String updatedBy;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String finYear;
	private String screenCode;
	private String screenName;
	private String ipNo;
	private String latitude;
}
