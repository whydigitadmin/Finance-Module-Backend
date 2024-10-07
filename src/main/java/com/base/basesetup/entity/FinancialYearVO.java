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
import com.fasterxml.jackson.annotation.JsonGetter;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialyeargen")
	@SequenceGenerator(name = "financialyeargen", sequenceName = "financialyearseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "financialyearid")
	private Long id;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private Long cancel;
	@Column(name = "cancelremarks")
	private Long cancelRemarks;
	@Column(name = "sno")
	private Long sno;
	@Column(name = "finyr")
	private String finYr;
	@Column(name="finyrid")
	private String finYrId;
	@Column(name = "finyridentifier")
	private String finYrIdentifier;
	@Column(name = "startdate")
	private LocalDate startDate;
	@Column(name = "enddate")
	private LocalDate endDate;
	@Column(name = "currentfinyr")
	private boolean currentFinYr;
	@Column(name = "closed")
	private boolean closed;
	@Column(name = "open")
	private boolean open;
	@Column(name="action")
	private boolean action;
	@Column(name = "company")
	private String company;
	@Column(name = "dupchk")
	private String dupchk;
	@Column(name = "userid")
	private String userId;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String  updatedBy;
	

	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
