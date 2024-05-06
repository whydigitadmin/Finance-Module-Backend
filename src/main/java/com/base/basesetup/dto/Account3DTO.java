package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account3DTO {
	private Long account3Id;
	private String company;
	private String branchLocation;

}
