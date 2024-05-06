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
	private Long gLId;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "groupname", length=50)
	private String groupNmae;
	@Column(name = "gsttaxflag")
	private boolean gstTaxFlag;
	@Column(name = "accountcode", length=20)
	private String accountCode;
	@Column(name = "coalist", length=30)
	private String coaList;
	@Column(name = "accountgroupname", length=30)
	private String accountgroupName;
	@Column(name = "type", length=30)
	private String type;
	@Column(name = "interbranchac", length=30)
	private boolean interBranchAc;
	@Column(name = "controllac", length=30)
	private boolean controllAc;
	@Column(name = "category", length=50)
	private String category;
	@Column(name = "currency", length=50)
	private String currency;
	@Column(name = "branch", length=50)
	private String branch;
	
	
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
