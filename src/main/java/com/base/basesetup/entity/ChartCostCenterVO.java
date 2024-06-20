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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chartcostcenter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartCostCenterVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "chartcostcentergen")
	@SequenceGenerator(name = "chartcostcentergen",sequenceName = "chartcostcenterVO",initialValue = 1000000001,allocationSize = 1)
	@Column(name="chartcostcenterid")
	private Long id;
	
	@Column(name = "costcentercode")
	private String costCenterCode;
	@Column(name = "costcentername")
	private String costCenterName;
	@Column(name = "credit")
	private String credit;
	@Column(name = "debit")
	private String debit;
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

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
