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
@Table(name = "reconcilebank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReconcileBankVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reconcilebankgen")
	@SequenceGenerator(name = "reconcilebankgen", sequenceName = "reconcilebankseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "reconcilebankid")
	private Long id;
	@Column(name = "docid")
	private String docId;
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@Column(name="bankaccount")
	private String bankAccount;
	@Column(name = "bankstmtdate")
	private LocalDateTime bankStmtDate;
	@Column(name="remarks")
	private String remarks;
	@Column(name="totaldeposit")
	private BigDecimal totalDeposit;
	@Column(name="totalwithdrawal")
	private BigDecimal totalWithdrawal;
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
	
	
	
	
	
	@OneToMany(mappedBy = "reconcileBankVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ParticularsReconcileVO> particularsReconcileVO;
	
	
}
