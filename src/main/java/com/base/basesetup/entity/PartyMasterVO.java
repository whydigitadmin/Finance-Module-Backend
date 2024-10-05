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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partymaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyMasterVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partymastergen")
	@SequenceGenerator(name = "partymastergen", sequenceName = "partymasterseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "partymasterid")
	private Long id;
	@Column(name = "partytype")
	private String partyType;
	@Column(name = "partycode")
	private String partyCode;
	@Column(name = "partyname")
	private String partyName;
	@Column(name = "gstpartyname")
	private String gstPartyName;
	@Column(name = "company")
	private String company;
	@Column(name = "customercategory")
	private String customerCategory;
	@Column(name = "agentname")
	private String agentName;
	@Column(name = "accountstype")
	private String accountsType;
	@Column(name = "bussinesstype")
	private String bussinessType;
	@Column(name = "carriercode")
	private String carrierCode;
	@Column(name = "suppliertype")
	private String supplierType;
	@Column(name = "salesperson")
	private String salesPerson;
	@Column(name = "salesperson1")
	private String salesPerson1;
	@Column(name = "customercoordinator")
	private String customerCoordinator;
	@Column(name = "customercoordinator1")
	private String customerCoordinator1;
	@Column(name = "accountname")
	private String accountName;
	@Column(name = "creditlimit")
	private String creditLimit;
	@Column(name = "creditdays")
	private String creditDays;
	@Column(name = "controllingoffice")
	private String controllingOffice;
	@Column(name = "currency")
	private String currency;
	@Column(name = "airwaybillcode")
	private String airWayBillCode;
	@Column(name = "airlinecode")
	private String airlineCode;
	@Column(name = "bussinesscategory")
	private String bussinessCategory;
	@Column(name = "bussinesscategory1")
	private String bussinessCategory1;
	@Column(name = "country")
	private String country;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "compoundingscheme")
	private String compoundingScheme;
	@Column(name = "gstregistration")
	private String gstRegistration;
	@Column(name = "panno")
	private String panNo;
	@Column(name = "panname")
	private String panName;
	@Column(name = "tanno")
	private String tanNo;
	@Column(name = "caf")
	private String caf;
	@Column(name = "psu")
	private String psu;
	
	@Column(name = "nameofbank")
	private String nameOfBank;
	@Column(name = "branch")
	private String branch;
	@Column(name = "addressofbranch")
	private String addressOfBranch;
	@Column(name = "accountno")
	private String accountNo;
	@Column(name = "accountype")
	private String accounType;
	@Column(name = "ifsccode")
	private String ifscCode;
	@Column(name = "swift")
	private String swift;

	
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String  updatedBy;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name="orgid")
	private Long orgId;
	@Column(name="active")
    private boolean active;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartyStateVO> partyStateVO;
	

	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartyAddressVO> partyAddressVO;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartyDetailsOfDirectorsVO> partyDetailsOfDirectorsVO;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartySpecialTDSVO> partySpecialTDSVO;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartyChargesExemptionVO> partyChargesExemptionVO;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartyCurrencyMappingVO> partyCurrencyMappingVO;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartySalesPersonTaggingVO> partySalesPersonTaggingVO;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartyTdsExemptedVO> partyTdsExemptedVO;
	
	@OneToMany(mappedBy = "partyMasterVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PartyPartnerTaggingVO> partyPartnerTaggingVO;
	
	@OneToOne(mappedBy = "partyMasterVO",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private PartyVendorEvaluationVO partyVendorEvaluationVO;
	
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	
	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}
}
