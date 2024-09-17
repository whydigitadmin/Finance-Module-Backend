package com.base.basesetup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibilitiesDTO {

	private Long id;
	private String role;
	private boolean active;
	private Long orgId;
	private String createdBy;
	
	private List<ScreenDTO> screenDTO;
}
