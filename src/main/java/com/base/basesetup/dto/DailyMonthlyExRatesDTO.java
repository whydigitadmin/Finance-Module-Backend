package com.base.basesetup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyMonthlyExRatesDTO {
	private Long id;
	private String date;
	private String month;
	private boolean active;
	private Long orgId;
	private String userId;	
	private String createdBy;
	private String updatedBy;

	List<DailyMonthlyExRatesDtlDTO> dailyMonthlyExRatesDtlDTO;
}