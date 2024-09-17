package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {
	private Long id;
	private String companyCode;
	private String companyName;
	private String country;
	private String currency;
	private String mainCurrency;
	private String address;
	private String zip;
	private String city;
	private String state;
	private String phone;
	private String email;
	private String webSite;
	private String note;
	private String employeeName;
	private String employeeCode;
	private String password;
	private String createdBy;
	private boolean active;
	private Long orgId;
	
}
