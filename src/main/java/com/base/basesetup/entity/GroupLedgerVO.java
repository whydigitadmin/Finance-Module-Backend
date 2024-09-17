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
@Table(name = "groupledger")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupLedgerVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "groupledgergen")
	@SequenceGenerator(name = "groupledgergen", sequenceName = "groupledgerseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "groupledgerid")
	private Long id;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "groupname")
	private String groupName;
	@Column(name = "gsttaxflag")
	private boolean gstTaxFlag;
	@Column(name = "accountcode")
	private String accountCode;
	@Column(name = "coalist")
	private String coaList;
	@Column(name = "accountgroupname")
	private String accountGroupName;
	@Column(name = "type")
	private String type;
	@Column(name = "interbranchac")
	private boolean interBranchAc;
	@Column(name = "controllac")
	private boolean controllAc;
	@Column(name = "category")
	private String category;
	@Column(name = "currency")
	private String currency;
	@Column(name = "branch")
	private String branch;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	private boolean cancel;
	private boolean active;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
