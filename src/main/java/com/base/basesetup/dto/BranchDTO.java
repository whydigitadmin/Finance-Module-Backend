package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDTO {
	private Long id;
	private String branch;
	private String branchCode;
	private Long orgId;
	private String addressLine1;
	private String addressLine2;
	private String pan;
	private String gstIn;
	private String phone;
	private String state;
	private String city;
	private String pinCode;
	private String country;
	private String stateNo;
	private String stateCode;
	private String region;
	private String lccurrency;
	private String createdBy;
	private String updatedBy;
	private String userId;
	private boolean active;
}
