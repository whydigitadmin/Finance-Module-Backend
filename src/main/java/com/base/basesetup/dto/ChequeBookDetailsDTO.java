package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChequeBookDetailsDTO {

	private String id;
	private Long chequeNo;
	private String status;
	private String cancelled;
	
	
}
