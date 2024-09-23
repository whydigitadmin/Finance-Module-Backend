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
@Table(name = "documenttype")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentTypeVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documenttypegen")
	@SequenceGenerator(name = "documenttypegen", sequenceName = "documenttypeseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "documenttypeid")
	private Long id;
	@Column(name = "screencode")
	private String screenCode;
	@Column(name = "screenname")
	private String screenName;
	@Column(name = "doccode")
	private String docCode;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "updatedby")
	private String updatedBy;
	@Column(name = "active")
	private boolean active;
	@Column(name = "orgid")
	private Long orgId;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
