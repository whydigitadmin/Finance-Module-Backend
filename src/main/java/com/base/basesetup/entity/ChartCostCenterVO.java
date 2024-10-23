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
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chartcostcenter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartCostCenterVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "chartcostcentergen")
	@SequenceGenerator(name = "chartcostcentergen",sequenceName = "chartcostcenterseq",initialValue = 1000000001,allocationSize = 1)
	@Column(name="chartcostcenterid")
	private Long id;
	
	@Column(name = "docid", length = 50)
    private String docId;

    @Column(name = "docdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate docDate = LocalDate.now();

	
	@Column(name = "costcentercode")
	private String costCenterCode;
	@Column(name = "costcentername")
	private String costCenterName;
	@Column(name = "credit")
	private Long credit;
	@Column(name = "debit")
	private Long debit;
	@Column(name = "orgid")
	private Long orgId;
	
    @Column(name = "branch", length = 25)
    private String branch;

    @Column(name = "branchcode", length = 20)
    private String branchCode;

    @Column(name = "createdby", length = 25)
    private String createdBy;

    @Column(name = "modifyby", length = 25)
    private String updatedBy;

    @Column(name = "active")
    private boolean active;

    @Column(name = "cancel")
    private boolean cancel;

    @Column(name = "cancelremarks", length = 50)
    private String cancelRemarks;

    @Column(name = "finyear", length = 5)
    private String finYear;

    @Column(name = "screencode", length = 5)
    private String screenCode = "CCC";

    @Column(name = "screenname", length = 25)
    private String screenName = "CHARTCOSTCENTER";

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
