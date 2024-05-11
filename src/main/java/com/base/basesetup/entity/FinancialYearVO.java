package com.base.basesetup.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "financialyear")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialYearVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "finyrid")
	private int id;
	@Column(name = "finyr")
	private int finYr;
	@Column(name = "finyridentifier")
	private int finYrIdentifier;
	@Column(name = "startdate")
	private LocalDate startDate;
	@Column(name = "enddate")
	private LocalDate endDate;
	@Column(name = "currentfinyr")
	private boolean currentFinYr;
	@Column(name = "closed")
	private boolean closed;
	@Column(name = "company")
	private String company;
	private String dupchk;
	@Column(name = "userid")
	private String userId;
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
