package com.base.basesetup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TcsMasterDTO {
	private Long Id;
	private Long orgId;
	private String section;
	private String sectionName;
	private boolean active;
	private String createdBy;
	private String updatedBy;

	private List<TcsMaster2DTO>  tcsMaster2DTO;
}
