package com.base.basesetup.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialYearDTO {
	private Long id;
	private Long orgId;
	private boolean active;
	private Long sno;
	private String finYr;
	private String finYrId;
	private String finYrIdentifier;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean currentFinYr;
	private boolean closed;
	private boolean open;
	private boolean action;
	private String company;
	private String userId;
	private String createdBy;

}
