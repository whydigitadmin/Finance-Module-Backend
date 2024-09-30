package com.base.basesetup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reconciliationsummary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReconciliationSummaryVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reconciliationsummarygen")
	@SequenceGenerator(name = "reconciliationsummarygen", sequenceName = "reconciliationsummaryseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "reconciliationsummaryid")
	private Long id;
	@Column(name = "taxcode")
	private String taxCode;
	@Column(name = "type")
	private String type;
	@Column(name = "rate")
	private String rate;
	@Column(name = "inputac")
	private String inputAc;
	@Column(name = "outputac")
	private String outputAc;
	
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;


}
