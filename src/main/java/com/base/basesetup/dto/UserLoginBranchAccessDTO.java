package com.base.basesetup.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginBranchAccessDTO {
	
	private Long id;
	private String branch;
	private String branchCode;
	

}