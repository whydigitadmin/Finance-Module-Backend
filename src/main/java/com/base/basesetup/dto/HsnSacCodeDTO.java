package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HsnSacCodeDTO {
	private Long id;
	private Long orgId;
	private boolean active;
	private String type;
	private String code;
	private String description;
	private String chapter;
	private String chapterCode;
	private String subChapter;
	private String subChapterCode;
	private float rate;
	private boolean excempted;
	private String createdBy;
	private String updatedBy;
}
