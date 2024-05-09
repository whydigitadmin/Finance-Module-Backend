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
@Table(name = "settaxrate")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetTaxRateVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "settaxrategen")
	@SequenceGenerator(name = "settaxrategen", sequenceName = "settaxrateseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "settaxrateid")
	private Long id;
	@Column(name = "orgid", length=20)
	private Long orgId;
	@Column(name = "chapter", length = 50)
	private String chapter;
	@Column(name = "subchapter", length = 50)
	private String subChapter;
	@Column(name = "hsncode", length = 20)
	private String hsnCode;
	@Column(name = "branchlocation", length = 50)
	private String branch;
	@Column(name = "newrate", precision = 15,scale = 7)
	private float newRate;
	@Column(name = "excepmted", length = 1)
	private String excepmted;
	@Column(name = "createdby", length=30)
	private String createdBy;
	@Column(name = "modifiedby", length=30)
	private String updatedBy;
	@Column(name = "cancelremarks", length=50)
	private String cancelRemarks;
	private boolean cancel;
	private boolean active;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	

}
