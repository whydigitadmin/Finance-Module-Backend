package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChequeBoxDTO {

	private Long id;
	private String branch;
	private String chequeBoxId;
	private String bank;
	private String checkPrefix;
	private String checkStartNo;
	private String noOfChequeLeaves;
	private Long orgId;
	private boolean active;
	private String createdBy;
	private String updatedBy;
}
 