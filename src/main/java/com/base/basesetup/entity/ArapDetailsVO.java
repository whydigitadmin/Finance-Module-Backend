package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "arapdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArapDetailsVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taxinvoicegen")
	@SequenceGenerator(name = "taxinvoicegen", sequenceName = "taxinvoiceseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "taxinvoiceid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	@Column(name = "finyr")
	private String finyr;
	@Column(name = "sourcetransid")
	private String sourceTransid;
	@Column(name = "docid")
	private String docId;
	@Column(name = "refno")
	private String refNo;
	@Column(name = "accountname")
	private String accountName;
	@Column(name = "currency")
	private String currency;
	@Column(name = "accountcurrency")
	private String accountCurrency;
	@Column(name = "account")
	private String account;
	@Column(name = "exrate")
	private BigDecimal exRate;
	@Column(name = "amount")
	private String amount;
	@Column(name = "baseamount")
	private String baseAmount;
	@Column(name = "nativeamount")
	private String nativeAmount;
	@Column(name = "mno")
	private String mno;
	@Column(name = "chargableAmount")
	private String chargableAmount;
	@Column(name = "gstflag")
	private String gstFlag;
	@Column(name = "doctypecode")
	private String docTypeCode;
	@Column(name = "subtypecode")
	private String subTypeCode;
	@Column(name = "subledgerdivision")
	private String subLedgerDivision;
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@Column(name = "supprefno")
	private String suppRefNo;
	@Column(name = "refdate")
	private LocalDateTime refDate;
	@Column(name = "supprefdate")
	private LocalDateTime suppRefDate;
	@Column(name = "subledgercode")
	private String subLedgerCode;
	@Column(name = "creditdays")
	private String creditDays;
	@Column(name = "duedate")
	private LocalDateTime dueDate;
	@Column(name = "tdsamount")
	private String tdsAmount;
	@Column(name = "hno")
	private String hno;
	@Column(name = "orgid")
	private String orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String canelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
