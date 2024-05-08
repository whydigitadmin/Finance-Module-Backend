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

    private Long gLId;
	
    private Long orgId;
    
	private String groupName;
	
	private boolean gstTaxflag;
	
	private String accountCode;
	
	private String coaList;
	
	private String accountgroupName;
	
	private String type;
	
	private boolean interBranchAc;
	
	private boolean controllAc;
	
	private String category;
	
	private String currency;
	
	private String branch;
	
	private boolean active;
	
}