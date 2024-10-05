package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountParticularsDTO {

	private Long id;
	private String accountName;
	private String subledger;
	private BigDecimal dbAmount;
	private BigDecimal crAmount;
	private String narration;
	

}
