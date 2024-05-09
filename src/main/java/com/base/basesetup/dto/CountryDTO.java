package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDTO {
	private Long id;
	private String countryName;
	private String countryCode;
	private Long orgId;
	private String userId;
}
