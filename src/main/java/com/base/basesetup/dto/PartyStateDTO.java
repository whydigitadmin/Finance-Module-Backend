package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyStateDTO {
	private Long id;
	private String state;
	private String gstIn;
	private String stateNo;
	private String contactPerson;
	private String contactPhoneNo;
	private String contactEmail;
	private String stateCode;
}