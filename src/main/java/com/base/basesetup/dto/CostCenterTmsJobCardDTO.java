package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostCenterTmsJobCardDTO {
	private Long id;
	private String accountName;
	private String amount;

}
