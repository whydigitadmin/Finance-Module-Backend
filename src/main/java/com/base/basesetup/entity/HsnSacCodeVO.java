package com.base.basesetup.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hsnsaccode")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HsnSacCodeVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hsnsaccodegen")
	@SequenceGenerator(name = "hsnsaccodegen", sequenceName = "hsnsaccodeseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "hsnsaccodeid")
	private Long id;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "type")
	private String type;
	@Column(name = "code")
	private String code;
	@Column(name = "descripition")
	private String descripition;
	@Column(name = "chapter")
	private String chapter;
	@Column(name = "chaptercode")
	private String chapterCode;
	@Column(name = "subchapter")
	private String subChapter;
	@Column(name = "subchaptercode")
	private String subChapterCode;
	@Column(name = "rate")
	private float rate;
	private boolean excempted;

	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "active")
	private boolean active;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
