package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeDTO {

	private Long id;
	private String screenCode;
	private String screenName;
	private String docCode;
	private String createdBy;
	private boolean active;
	private Long orgId;
}
