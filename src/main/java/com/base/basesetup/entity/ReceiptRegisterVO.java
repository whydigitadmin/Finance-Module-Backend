package com.base.basesetup.entity;

import java.math.BigDecimal;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="receiptregister")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptRegisterVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "receiptregistergen")
	@SequenceGenerator(name= "receiptregistergen",sequenceName = "receiptregisterseq",initialValue = 1000000001,allocationSize = 1)
	
	@Column(name="receiptregisterid")
	private Long id;
	@Column(name="voucherno")
	private Long voucherNo;
	@Column(name="voucherdate")
	private LocalDate voucherDate;
	@Column(name="receivedfrom")
	private String receivedFrom;
	@Column(name="currency")
	private String currency;
	@Column(name="exrate")
	private BigDecimal exRate;
	@Column(name="amount")
	private Long amount;
	@Column(name="lcamount")
	private Long lcAmount;
	
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
