package com.base.basesetup.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TdsCostInvoiceDTO {
	private Long id;
	private String tdsWh;
	private BigDecimal tdsWhPercent;
	private String section;
	private String totalTds;

}
