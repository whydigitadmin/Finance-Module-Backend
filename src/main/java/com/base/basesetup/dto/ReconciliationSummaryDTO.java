package com.base.basesetup.dto;

import javax.persistence.Column;

import com.base.basesetup.entity.ReconciliationSummaryVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReconciliationSummaryDTO {

	private Long id;
	private String taxCode;
	private String type;
	private String rate;
	private String inputAc;
	private String outputAc;
	
	private Long orgId;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String createdBy;
}
