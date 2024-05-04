package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxMaster2DTO {

	private Long taxMaster2Id;
	private Long inputAccount;
	private Long outputAccount;
	private boolean sgstRcmPayable;

}
