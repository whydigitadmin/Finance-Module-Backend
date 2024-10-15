package com.base.basesetup.entity;

import java.time.LocalDate;
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
@Table(name = "reconcilecorpbank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReconcileCorpBankVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reconcilecorpbankgen")
	@SequenceGenerator(name = "reconcilecorpbankgen", sequenceName = "reconcilecorpbankseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "reconcilecorpbankid")
	private Long id;
	@Column(name = "docid")
	private String docId;
	@Column(name = "docdate")
	private LocalDate docDate;
	@Column(name="bankaccount")
	private String bankAccount;
	@Column(name = "bankstmtdate")
	private LocalDate bankStmtDate;
	@Column(name="remarks")
	private String remarks;
	
	@Builder.Default
	@Column(name = "screencode")
	private String screenCode="RC";
	@Builder.Default
	@Column(name="screenname")
	private String screenName="RECONCILE CORP";
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

	@OneToMany(mappedBy = "reconcileCorpBankVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ParticularsReconcileCorpBankVO> particularsReconcileCorpBankVO;
}
