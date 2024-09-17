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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brsopening")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrsOpeningVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brsopeninggen")
	@SequenceGenerator(name = "brsopeninggen", sequenceName = "brsopeningseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "brsopeningid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	@Column(name = "billno")
	private String billno;
	@Column(name = "billdate")
	private LocalDateTime billDate;
	@Column(name = "chqno")
	private String chqNo;
	@Column(name = "chqdate")
	private LocalDateTime chqDate;
	@Column(name = "bank")
	private String bank;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private String exRate;
	@Column(name = "receiptamount")
	private String receiptAmount;
	@Column(name = "paymentamount")
	private String paymentAmount;
	@Column(name = "reconcile")
	private boolean reconcile;
	@Column(name = "active")
	private boolean active;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "cancel")
	private String cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "updatedby")
	private String updatedBy;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
