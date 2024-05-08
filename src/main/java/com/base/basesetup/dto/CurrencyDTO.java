package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyDTO {

	private Long id;
	private String userid;
	private String country;
	private String currency;
	private String subCurrency;
	private String currencySymbol;
	private Long orgId;
}
