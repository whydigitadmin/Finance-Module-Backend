package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyAddressDTO {

    private Long id;
	private String state;
	private String businessPlace;
	private String stateGstIn;
	private String cityName;
	private String addressType;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String pincode;
	private String contactPerson;
	private String contactPhoneNo;
	private String contactEmail;
}