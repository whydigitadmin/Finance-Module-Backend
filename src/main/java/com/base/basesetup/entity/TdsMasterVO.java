package com.base.basesetup.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tdsmaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TdsMasterVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tdsmastergen")
	@SequenceGenerator(name = "tdsmastergen", sequenceName = "tdsmasterseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "tdsmasterid")
	private Long tdsMasterId;
	@Column(name = "orgid", length=20)
	private Long orgId;
	@Column(name = "section", length=10)
	private String section;
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
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tdsMasterVO", cascade = CascadeType.ALL)
	private List<TdsMaster2VO> tdsMaster2VO;
	

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
