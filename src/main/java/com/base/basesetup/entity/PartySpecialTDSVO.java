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
@Table(name = "partyspecialtds")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartySpecialTDSVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partyspecialtdsgen")
	@SequenceGenerator(name = "partyspecialtdsgen", sequenceName = "partyspecialtdsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "partyspecialtdsid")
	private Long id;
	@Column(name = "tdssection")
	private String tdsSection;
	@Column(name = "rateform")
	private String rateForm;
	@Column(name = "rateto")
	private String rateTo;
	@Column(name = "tdspercentage")
	private String tdsPercentage;
	@Column(name = "surpercentage")
	private String surPercentage;
	@Column(name = "edpercentage")
	private String edPercentage;
	@Column(name = "tdscertificateno")
	private String tdsCertificateNo;
	
	
	@ManyToOne
	@JoinColumn(name = "partymasterid")
	@JsonBackReference
	private PartyMasterVO partyMasterVO;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
