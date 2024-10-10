package com.base.basesetup.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticularsAccountReceiptDTO {
	private Long id;
	private LocalDate fromDate;
	private LocalDate toDate;
    private Long tcs;

}
