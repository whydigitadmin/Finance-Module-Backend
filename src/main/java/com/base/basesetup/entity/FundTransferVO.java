package com.base.basesetup.entity;

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
@Table(name = "fundtransfer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundTransferVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fundtransfergen")
	@SequenceGenerator(name = "fundtransfergen", sequenceName = "fundtransferVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "fundtransferid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	@Column(name = "docid")
	private String docId;
	@Column(name = "paymenttype")
	private String paymentType;
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@Column(name = "referenceno")
	private String referenceNo;
	@Column(name = "referencedate")
	private LocalDateTime referenceDate;
	@Column(name = "fromaccount")
	private String fromAccount;
	@Column(name = "balance")
	private String balance;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private String exRate;
	@Column(name = "tobranch")
	private String toBranch;
	@Column(name = "tobank")
	private String toBank;
	@Column(name = "chequebook")
	private String chequeBook;
	@Column(name = "chequeno")
	private String chequeNo;
	@Column(name = "chequedate")
	private LocalDateTime chequeDate;
	@Column(name = "paymentamount")
	private String paymentAmount;
	@Column(name = "conversionrate")
	private String conversionRate;
	@Column(name = "receiptamount")
	private String receiptAmount;
	@Column(name = "gainLoss")
	private String gainLoss;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "active")
	private boolean active;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
