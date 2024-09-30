package com.base.basesetup.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reconcile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReconcileVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reconcilegen")
	@SequenceGenerator(name = "reconcilegen", sequenceName = "reconcileseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "reconcileid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	@Column(name = "docid")
	private String docId;
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@Column(name = "bankstmtdate")
	private LocalDateTime bankStmtDate;
	@Column(name="bankaccount")
	private String bankAccount;
	@Column(name = "lastreconciledon")
	private LocalDateTime lastReconciledOn;
	@Column(name = "cleareddate")
	private LocalDateTime clearedDate;
	@Column(name="ledgerbalance")
	private BigDecimal ledgerBalance;
	@Column(name="beginingbalance")
	private BigDecimal beginingBalance;
	@Column(name="endingbalance")
	private BigDecimal endingBalance;
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
	
//	@Column(name = "invoiceno")
//	private String invoiceNo;
//	@Column(name = "invoicedate")
//	private LocalDateTime invoiceDate;
//	@Column(name = "finyr")
//	private String finyr;
	
	
	
	@Column(name="totalwithdrawal")
	private BigDecimal totalWithdrawal;
	@Column(name="totaldeposit")
	private BigDecimal totalDeposit;
	@Column(name="summaryendingbalance")
	private BigDecimal summaryEndingBalance;
	@Column(name="clearedbalance")
	private BigDecimal clearedBalance;
	@Column(name="difference")
	private BigDecimal difference;
	@Column(name="narration")
	private String narration;
	
	
//	@PrePersist
//	private void setDefaultFinyr() {
//		// Execute the logic to set the default value for finyr
//		String fyFull = calculateFinyr();
//		this.finyr = fyFull;
//	}
//
//	private String calculateFinyr() {
//		// Logic to calculate finyr based on the provided SQL query
//		String currentMonthDay = LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"));
//		String fyFull = (currentMonthDay.compareTo("0331") > 0)
//				? LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"))
//				: LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy"));
//		return fyFull;
//	}
	
	
	
	
	
	@OneToMany(mappedBy = "reconcileVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	List<WithdrawalsReconcileVO> withdrawalsReconcileVO;
	
	@OneToMany(mappedBy = "reconcileVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	List<DepositsReconcileVO> depositsReconcileVO;
}
