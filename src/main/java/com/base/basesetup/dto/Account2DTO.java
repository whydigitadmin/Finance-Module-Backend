package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Account2DTO {
	
	private Long account2Id;
	private String bankType;
	private Long accountNo;
	private Long overDraftLimit;

}