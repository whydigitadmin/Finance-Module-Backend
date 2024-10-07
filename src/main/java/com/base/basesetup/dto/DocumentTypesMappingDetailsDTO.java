package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypesMappingDetailsDTO {
	
	private Long id;
	private String screenName;
	private String screenCode;
	private String finYear;
	private String branch;
	private String branchCode;
	private String finyearId;
	private String docCode;
	private String prefix;
	private String lastNo;
}
