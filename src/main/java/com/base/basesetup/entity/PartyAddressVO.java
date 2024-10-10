package com.base.basesetup.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partyaddress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyAddressVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partyaddressgen")
	@SequenceGenerator(name = "partyaddressgen", sequenceName = "partyaddressseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "partyaddressid")
	private Long id;
	
	@Column(name = "state")
	private String state;
	@Column(name = "businessplace")
	private String businessPlace;
	@Column(name = "stategstin")
	private String stateGstIn;
	@Column(name = "cityname")
	private String cityName;
	@Column(name = "addresstype")
	private String addressType;
	@Column(name = "addressline1")
	private String addressLine1;
	@Column(name = "addressline2")
	private String addressLine2;
	@Column(name = "addressline3")
	private String addressLine3;
	@Column(name = "pincode")
	private String pincode;
	@Column(name = "contactperson")
	private String contactPerson;
	@Column(name = "contactphoneno")
	private String contactPhoneNo;
	@Column(name = "contactemail")
	private String contactEmail;
	

	@ManyToOne
	@JoinColumn(name = "partymasterid")
	@JsonBackReference
	private PartyMasterVO partyMasterVO;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}