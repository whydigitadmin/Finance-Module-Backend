package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
	private Long id;
	private String cityCode;
	private String country;
	private String cityName;
	private String state;
	private String userId;
	private Long orgId;
}
