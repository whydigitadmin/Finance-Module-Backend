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
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "accountgen")
	@SequenceGenerator(name = "accountgen", sequenceName = "accountseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "accountid")
	private Long accountId;
	@Column(name = "accountgroup", length = 50)
	private String accountGroup;
	@Column(name = "branchlocation", length = 50)
	private String branchLocation;
	@Column(name = "accounttype", length = 50)
	private String accountType;
	@Column(name = "groupname", length = 50)
	private String groupName;
	@Column(name = "accountcode", length = 50)
	private String accountCode;
	@Column(name = "accountgroupname", length = 50)
	private String accountGroupName;
	@Column(name = "currency", length = 50)
	private String currency;
	@Column(name = "category", length = 50)
	private String category;
	@Column(name = "block")
	private boolean block;
	@Column(name = "isitcapplicable")
	private boolean isItcApplicable;

	@Column(name = "createdby", length = 30)
	private String createdBy;
	@Column(name = "updatedby", length = 30)
	private String updatedBy;
	@Column(name = "cancelremarks", length = 50)
	private String cancelRemarks;
	private boolean cancel;
	private boolean active;
	
	@OneToMany(mappedBy = "accountVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Account1VO> account1VO;
	
	@OneToMany(mappedBy = "",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Account2VO> account2VO;
	
	@OneToMany(mappedBy = "",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Account3VO> account3VO;
	
	
	

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
