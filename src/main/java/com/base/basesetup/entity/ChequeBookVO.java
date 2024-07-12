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
@Table(name = "chequeboxmaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChequeBookVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chequeboxmastergen")
	@SequenceGenerator(name = "chequeboxmastergen", sequenceName = "chequeboxmasterseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "chequeboxmasterid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	@Column(name = "chequeboxid")
	private String chequeBoxId;
	@Column(name = "bank")
	private String bank;
	@Column(name = "checkprefix")
	private String checkPrefix;
	@Column(name = "checkstartno")
	private String checkStartNo;
	@Column(name = "noofchequeleaves")
	private String noOfChequeLeaves;
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
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
