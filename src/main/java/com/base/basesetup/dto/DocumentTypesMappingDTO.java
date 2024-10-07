package com.base.basesetup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypesMappingDTO {
	
	private Long id;
	private String finYear;
	private String branch;
    private boolean active;
	private String createdBy;
	private Long orgId;
	
	List<DocumentTypesMappingDetailsDTO> documentTypeMappingDetailsDTO;
}
