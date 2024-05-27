package com.base.basesetup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxMasterDTO {
	
	private Long Id;
	private Long orgId;
	private String taxType;
	private float taxPercentage;
	private String taxDescription;
	private boolean active;
	
	private List<TaxMaster2DTO>  taxMaster2DTO;
}
