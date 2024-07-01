package com.base.basesetup.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.base.basesetup.entity.ParticularsJournalVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralJournalDTO {
	private Long id;
	private String branch;
	private String voucherType;
	private LocalDateTime docDate;
	private String docId;
	private String template;
	private String currency;
	private String exRate;
	private String refNo;
	private LocalDateTime refDate;
	private LocalDateTime reverseOn;
	private String narration;
	private String orgId;
	private boolean active;
	private String createdBy;
	private String updatedBy;

	List<ParticularsJournalDTO> particularJournalDTO;

}
