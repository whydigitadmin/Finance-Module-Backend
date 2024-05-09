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
@Table(name = "taxmaster2")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxMaster2VO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "taxmaster2gen")
	@SequenceGenerator(name = "taxmaster2gen", sequenceName = "taxmaster2seq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "taxmaster2id")
	private Long id;
	
	@Column(name = "inputaccount", length=20)
	private Long inputAccount;
	@Column(name = "outputaccount", length=20)
	private Long outputAccount;
	@Column(name = "sgstrcmpayable")
	private boolean sgstRcmPayable;
	


	@ManyToOne
	@JoinColumn(name="taxmasterid")
	@JsonBackReference
	private TaxMasterVO taxMasterVO;
	
	

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
