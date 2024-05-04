package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HsnSacCodeDTO {
	private Long hsnSacCodeId;
	private Long orgId;
	private String type;
	private String code;
	private String description;
	private String chapter;
	private String chapterCode;
	private String subChapter;
	private String subChapterCode;
	private float rate;
	private boolean excempted;
}
