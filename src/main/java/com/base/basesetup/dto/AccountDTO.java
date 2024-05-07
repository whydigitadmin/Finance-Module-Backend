package com.base.basesetup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

	private Long accountId;
	
	private Long orgId;

	private String accountGroup;

	private String branchLocation;

	private String accountType;

	private String groupName;

	private String accountCode;

	private String accountGroupName;

	private String currency;

	private String category;

	private boolean block;

	private boolean isItcApplicable;
	
	private boolean active;
	
	
	private List<Account1DTO> account1DTO;
	
	private List<Account2DTO> account2DTO;
	
	private List<Account3DTO> account3DTO;
	
	

}
