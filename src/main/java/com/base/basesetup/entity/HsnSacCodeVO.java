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
	private Long hsnSacCodeId;
	@Column(name = "orgid", length=20)
	private Long orgId;
	@Column(name = "type", length=10)
	private String type;
	@Column(name = "code", length=10)
	private String code;
	@Column(name = "description", length=100)
	private String description;
	@Column(name = "chapter", length=10)
	private String chapter;
	@Column(name = "chaptercode", length=10)
	private String chapterCode;
	@Column(name = "subchapter", length=10)
	private String subChapter;
	@Column(name = "subchaptercode", length=10)
	private String subChapterCode;
	@Column(name = "rate", precision = 15,scale = 7)
	private float rate;
	private boolean excempted;
	
	@Column(name = "createdby", length=30)
	private String createdBy;
	@Column(name = "updatedby", length=30)
	private String updatedBy;
	@Column(name = "cancelremarks", length=50)
	private String cancelRemarks;
	private boolean cancel;
	private boolean active;

	
	
	
	
	
	
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
