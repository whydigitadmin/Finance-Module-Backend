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
@Table(name = "partytdsexempted")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyTdsExemptedVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partytdsexemptedgen")
	@SequenceGenerator(name = "partytdsexemptedgen", sequenceName = "partytdsexemptedseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "partytdsexemptedid")
	private Long id;
	@Column(name = "tdsexemptedcertificate")
	private String tdsExemptedCertificate;
	@Column(name = "value")
	private String value;
	@Column(name = "finyear")
	private String finYear;
	
	@ManyToOne
	@JoinColumn(name = "partymasterid")
	@JsonBackReference
	private PartyMasterVO partyMasterVO;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	
}