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
@Table(name = "tcsmaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TcsMasterVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tcsmastergen")
	@SequenceGenerator(name = "tcsmastergen", sequenceName = "tcsmasterseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "tcsmasterid")
	private Long tcsMasterId;
	@Column(name = "orgid", length=20)
	private Long orgId;
	@Column(name = "section", length=10)
	private  String section;
	@Column(name = "sectionname", length=50)
	private String sectionName;
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
