package com.base.basesetup.entity;

import java.time.LocalDate;

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
@Table(name = "exrates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExRatesVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exratesgen")
	@SequenceGenerator(name = "exratesgen", sequenceName = "exratesseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "exratesid")
	private Long id;
	@Column(name = "docdate")
	private LocalDate docDate;
	@Column(name = "docmonth")
	private LocalDate docMonth;
	@Column(name = "currency")
	private String currency;
	@Column(name = "sellrate")
	private String sellRate;
	@Column(name = "buyrate")
	private String buyRate;
	@Column(name = "avgrate")
	private String avgRate;
	@Column(name = "orgid")
	private Long orgId;

	@Column(name = "active")
	private boolean active;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancel")
	private boolean cancel;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
