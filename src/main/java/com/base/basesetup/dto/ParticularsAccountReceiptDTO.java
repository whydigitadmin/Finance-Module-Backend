package com.base.basesetup.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticularsAccountReceiptDTO {
	private Long id;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
    private Long tcs;

}
