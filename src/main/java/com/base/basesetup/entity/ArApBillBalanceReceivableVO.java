package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "arapbillbalancerecievable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArApBillBalanceReceivableVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arapbillbalancerecievablegen")
	@SequenceGenerator(name = "arapbillbalancerecievablegen", sequenceName = "arapbillbalancerecievableseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "arapbillbalancerecievableid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	@Column(name = "partyname")
	private String partyName;
	@Column(name = "partycode")
	private String partyCode;
	@Column(name = "currency")
	private String currency;
	@Column(name = "creditdays")
	private String creditDays;
	@Column(name = "billno")
	private String billNo;
	@Column(name = "billdate")
	private LocalDate billDate;
	@Column(name = "billexrate")
	private String billExRate;
	@Column(name = "supplierredno")
	private String supplierRefNo;
	@Column(name = "supplierrefdate")
	private LocalDate supplierRefDate;
	@Column(name = "duedate")
	private LocalDate dueDate;
	@Column(name = "debitamount")
	private BigDecimal debitAmount;
	@Column(name = "creditamount")
	private BigDecimal creditAmount;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
