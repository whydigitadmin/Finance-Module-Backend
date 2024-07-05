package com.base.basesetup.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryDebitNoteDTO {
	private Long id;
	private String billCurrTotChargeAmount;
	private String billCurrTotGrossAmount;
	private String billCurrNetAmount;
	private String amountInWords;
	private String roundOff;
	private String lctotChargeAmount;
	private String lctotGrossAmount;
	private String lcNetAmount;
}
