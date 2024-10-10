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
@Table(name = "reconcilecash")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReconcileCashVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reconcilecashgen")
	@SequenceGenerator(name = "reconcilecashgen", sequenceName = "reconcilecashseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "reconcilecashid")
	private Long id;
	@Column(name = "docid")
	private String docId;
	@Column(name = "docdate")
	private LocalDate docDate;
	@Column(name="accountname")
	private String cashAccount;
	@Column(name="bookbalance")
	private BigDecimal balanceAsPerBooks;
	@Column(name="dn1")
	private int dn1;
	@Column(name="dn2")
	private int dn2;
	@Column(name="dn3")
	private int dn3;
	@Column(name="dn4")
	private int dn4;
	@Column(name="dn5")
	private int dn5;
	@Column(name="dn6")
	private int dn6;
	@Column(name="dn7")
	private int dn7;
	@Column(name="dn8")
	private int dn8;
	@Column(name="dn1amt")
	private BigDecimal dn1Amt;
	@Column(name="dn2amt")
	private BigDecimal dn2Amt;
	@Column(name="dn3amt")
	private BigDecimal dn3Amt;
	@Column(name="dn4amt")
	private BigDecimal dn4Amt;
	@Column(name="dn5amt")
	private BigDecimal dn5Amt;
	@Column(name="dn6amt")
	private BigDecimal dn6Amt;
	@Column(name="dn7amt")
	private BigDecimal dn7Amt;
	@Column(name="dn8amt")
	private BigDecimal dn8Amt;
	@Column(name="totalpamount")
	private int totalPhyAmount ;
	@Column(name="diffamount")
	private BigDecimal differenceAmount;
	@Column(name="remarks")
	private String remarks;
	
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	
}

