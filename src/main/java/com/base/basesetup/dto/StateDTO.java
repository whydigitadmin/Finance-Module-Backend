package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
	private Long id;
	private String stateCode;
	private String stateName;
	private String country;
	private String region;
	private int stateNumber;
	private String userId;
	private Long orgId;
	private boolean active;
}
